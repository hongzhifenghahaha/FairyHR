package org.fairysoftw.fairyhr.tag;

import org.fairysoftw.fairyhr.model.LeaveRequest;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * @author WarmCongee
 * @date 2021/12/5 17:03
 */
public class CheckedLeaveListItemTag extends SimpleTagSupport {
    private LeaveRequest item;

    public void setItem(LeaveRequest item) {
        this.item = item;
    }


    @Override
    public void doTag() throws JspException, IOException {
        var context = "<tr>"+
                "<td>"+item.getSubmitTime()+"</td>"+
                "<td>"+item.getUser().getId()+"</td>"+
                "<td>"+item.getUser().getName()+"</td>"+
                "<td>"+item.getStartTime()+"</td>"+
                "<td>"+item.getEndTime()+"</td>"+
                "<td>"+item.getChecker().getName()+" "+ item.getChecker().getId() +"</td>"+
                "<td>"+item.getStatus()+"</td>"+
                "<td>"+item.getCheckOpinion()+"</td>"+
                "<td>"+item.getCheckTime()+"</td>"+
                "</tr>"
                ;
        JspWriter out = getJspContext().getOut();
        out.print(context);
    }

}
