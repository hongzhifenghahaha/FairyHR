package org.fairysoftw.fairyhr.tag;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.IOException;


/**
 * 页脚tag，使用SimpleTag来实现这个功能
 *
 * @version 1.0
 */
public class FooterTag extends SimpleTagSupport {
    /**
     * 打印页脚。
     */
    public void doTag() throws JspException, IOException {
        var context = """
                <footer class="content-footer">
                    <div class="footer">
                        <div class="copyright">
                            <span>Copyright © 2021 <b class="text-dark">FairySoftware</b>. All Right Reserved.</span>
                        </div>
                        <div id="datatable"></div>
                    </div>
                </footer>""";
        JspWriter out = getJspContext().getOut();
        out.print(context);
    }
}
