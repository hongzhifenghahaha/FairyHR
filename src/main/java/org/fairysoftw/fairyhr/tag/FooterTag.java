package org.fairysoftw.fairyhr.tag;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.IOException;


/**
 * 页脚tag
 */
public class FooterTag extends SimpleTagSupport {
    public void doTag() throws JspException, IOException {
        var context = """
                <footer class="content-footer">
                    <div class="footer">
                        <div class="copyright">
                            <span>Copyright © 2018 <b class="text-dark">UIdeck</b>. All Right Reserved More Templates <a
                                href="http://www.scnoob.com">菜鸟素材</a> - Collect from <a href="http://www.scnoob.com/moban">网页模板</a></span>
                        </div>
                        <div id="datatable"></div>
                    </div>
                </footer>""";
        JspWriter out = getJspContext().getOut();
        out.print(context);
    }
}
