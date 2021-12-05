package org.fairysoftw.fairyhr.tag;

import org.apache.taglibs.standard.lang.jstl.ELException;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.fairysoftw.fairyhr.model.LeaveRequest;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.el.VariableResolver;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author WarmCongee
 * @date 2021/12/5 15:32
 */
public class LeaveListItemTag extends SimpleTagSupport {

    private LeaveRequest item;

    public void setItem(LeaveRequest item) {
        this.item = item;
    }

    public LeaveRequest getItem() {
        return item;
    }
    /*private String submitTime;
    private String id;
    private String name;
    private String startTime;
    private String endTime;
    private String reason;
    private String status;

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setStatus(String status) {
        this.status = status;
    }*/


    @Override
    public void doTag() throws JspException, IOException {
        var context = "<tr>"+
                "<td>"+item.getSubmitTime()+"</td>"+
                "<td>"+item.getUser().getId()+"</td>"+
                "<td>"+item.getUser().getName()+"</td>"+
                "<td>"+item.getStartTime()+"</td>"+
                "<td>"+item.getEndTime()+"</td>"+
                "<td>"+item.getReason()+"</td>"+
                "<td>"+item.getStatus()+"</td>"+
                "</tr>"
                ;
        JspWriter out = getJspContext().getOut();
        out.print(context);
    }

}
