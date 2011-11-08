package Controller.actions.Dept;

import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import Model.DAO.*;
import org.apache.commons.lang3.*;

/**
 * Submitting action of departments` search
 * @author sikorskyi
 */
public class FindDeptSubmit implements IAction {

    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws ModelException {
        HttpSession session = request.getSession();
        String dname = request.getParameter(Constants.DNAME);
        String loc = request.getParameter(Constants.LOC);
        if (StringUtils.isEmpty(dname) &&  (StringUtils.isEmpty(loc))) {
            throw new ModelException("You do not enter any data");
        } else if ((!StringUtils.isEmpty(dname)) && (!StringUtils.isEmpty(loc))) {
            session.setAttribute(Constants.DEPT_LIST, DAOFactory.getModel(ModelType.Local).findDepts(dname, loc));
        } else if ((!StringUtils.isEmpty(dname)) && (StringUtils.isEmpty(loc))) {
            session.setAttribute(Constants.DEPT_LIST, DAOFactory.getModel(ModelType.Local).findDeptsByDname(dname));
        } else if ((StringUtils.isEmpty(dname)) && (!StringUtils.isEmpty(loc))) {
            session.setAttribute(Constants.DEPT_LIST, DAOFactory.getModel(ModelType.Local).findDeptsByLoc(loc));
        }
        return Constants.REDIRECT_DEPT_TABLE;
    }
}
