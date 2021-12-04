function init() {
    var $ = go.GraphObject.make;  // for conciseness in defining templates

    myDiagram =
        $(go.Diagram, "myDiagramDiv", // must be the ID or reference to div
            {
                initialAutoScale: go.Diagram.Uniform,
                maxSelectionCount: 1, // users can select only one part at a time
                validCycle: go.Diagram.CycleDestinationTree, // make sure users can only create trees
                "clickCreatingTool.archetypeNodeData": { // allow double-click in background to create a new node
                    name: "(new person)",
                    title: "",
                    comments: ""
                },
                "clickCreatingTool.insertPart": function (loc) {  // scroll to the new node
                    var node = go.ClickCreatingTool.prototype.insertPart.call(this, loc);
                    if (node !== null) {
                        this.diagram.select(node);
                        this.diagram.commandHandler.scrollToPart(node);
                        this.diagram.commandHandler.editTextBlock(node.findObject("NAMETB"));
                    }
                    return node;
                },
                layout:
                    $(SideTreeLayout,
                        {
                            treeStyle: go.TreeLayout.StyleLastParents,
                            arrangement: go.TreeLayout.ArrangementHorizontal,
                            // properties for most of the tree:
                            angle: 90,
                            layerSpacing: 35,
                            // properties for the "last parents":
                            alternateAngle: 90,
                            alternateLayerSpacing: 35,
                            alternateAlignment: go.TreeLayout.AlignmentBus,
                            alternateNodeSpacing: 20
                        }),
                "undoManager.isEnabled": true // enable undo & redo
            });

    myDiagram.isReadOnly = true;

    var levelColors = ["#494949", "#F13974"];

    // override TreeLayout.commitNodes to also modify the background brush based on the tree depth level
    myDiagram.layout.commitNodes = function () {
        go.TreeLayout.prototype.commitNodes.call(myDiagram.layout);  // do the standard behavior
        // then go through all of the vertexes and set their corresponding node's Shape.fill
        // to a brush dependent on the TreeVertex.level value
        myDiagram.layout.network.vertexes.each(function (v) {
            if (v.node) {
                var color = levelColors[0];
                if (v.node.ob.enable) {
                    color = levelColors[1];
                }
                var shape = v.node.findObject("SHAPE");
                if (shape) shape.fill = $(go.Brush, "Linear", {
                    0: color,
                    1: go.Brush.lightenBy(color, 0.05),
                    start: go.Spot.Left,
                    end: go.Spot.Right
                });
            }
        });
    };

    // when a node is double-clicked, add a child to it
    function nodeDoubleClick(e, obj) {
        var clicked = obj.part;
        if (clicked !== null) {
            var thisemp = clicked.data;
            if (thisemp.enable) {
                let root = "department/" + thisemp.key;
                window.location.href = root;
            }
        }
    }

    // this is used to determine feedback during drags
    function mayWorkFor(node1, node2) {
        if (!(node1 instanceof go.Node)) return false;  // must be a Node
        if (node1 === node2) return false;  // cannot work for yourself
        if (node2.isInTreeOf(node1)) return false;  // cannot work for someone who works for you
        return true;
    }

    // This function provides a common style for most of the TextBlocks.
    // Some of these values may be overridden in a particular TextBlock.
    function textStyle() {
        return {font: "9pt  Segoe UI,sans-serif", stroke: "white"};
    }

    // define the Node template
    myDiagram.nodeTemplate =
        $(go.Node, "Auto",
            {doubleClick: nodeDoubleClick},
            // for sorting, have the Node.text be the data.name
            new go.Binding("text", "name"),
            // define the node's outer shape
            $(go.Shape, "Rectangle",
                {
                    name: "SHAPE", fill: "white", stroke: null,
                    // set the port properties:
                    portId: "", fromLinkable: true, toLinkable: true, cursor: "pointer"
                }),
            $(go.Panel, "Horizontal",
                $(go.Panel, "Table",
                    {
                        maxSize: new go.Size(150, 999),
                        minSize: new go.Size(140, 80),
                        defaultAlignment: go.Spot.Center
                    },
                    $(go.TextBlock, textStyle(),  // the name
                        {
                            name: "NAMETB",
                            font: "12pt Segoe UI,sans-serif",
                            editable: false,
                            isMultiline: false,
                            alignment: go.Spot.Center
                        },
                        new go.Binding("text", "name")),
                )  // end Table Panel
            ) // end Horizontal Panel
        );  // end Node

    // define the Link template
    myDiagram.linkTemplate =
        $(go.Link, go.Link.Orthogonal,
            {corner: 5, relinkableFrom: true, relinkableTo: true},
            $(go.Shape, {strokeWidth: 4, stroke: "#00a4a4"}));  // the link shape

    // read in the JSON-format data from the "mySavedModel" element
    load();
}

// Assume that the SideTreeLayout determines whether a node is an "assistant" if a particular data property is true.
// You can adapt this code to decide according to your app's needs.
function isAssistant(n) {
    if (n === null) return false;
    return n.data.isAssistant;
}


// This is a custom TreeLayout that knows about "assistants".
// A Node for which isAssistant(n) is true will be placed at the side below the parent node
// but above all of the other child nodes.
// An assistant node may be the root of its own subtree.
// An assistant node may have its own assistant nodes.
function SideTreeLayout() {
    go.TreeLayout.call(this);
}

go.Diagram.inherit(SideTreeLayout, go.TreeLayout);

SideTreeLayout.prototype.assignTreeVertexValues = function (v) {
    // if a vertex has any assistants, use Bus alignment
    var any = false;
    var children = v.children;
    for (var i = 0; i < children.length; i++) {
        var c = children[i];
        if (isAssistant(c.node)) {
            any = true;
            break;
        }
    }
    if (any) {
        // this is the parent for the assistant(s)
        v.alignment = go.TreeLayout.AlignmentBus;  // this is required
        v.nodeSpacing = 50; // control the distance of the assistants from the parent's main links
    } else if (v.node == null && v.childrenCount > 0) {
        // found the substitute parent for non-assistant children
        //v.alignment = go.TreeLayout.AlignmentCenterChildren;
        //v.breadthLimit = 3000;
        v.layerSpacing = 0;
    }
};

// Show the diagram's model in JSON format
function save() {
    document.getElementById("model").value = myDiagram.model.toJSON();
    myDiagram.isModified = false;
}

function load() {
    myDiagram.model = go.Model.fromJson(document.getElementById("model").value);
    // make sure new data keys are unique positive integers
    var lastkey = 1;
    myDiagram.model.makeUniqueKeyFunction = function (model, data) {
        var k = data.key || lastkey;
        while (model.findNodeDataForKey(k)) k++;
        data.key = lastkey = k;
        return k;
    };
}

window.addEventListener('DOMContentLoaded', init);

