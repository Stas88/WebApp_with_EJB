package Controller.actions.Emp;

import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import Model.DAO.*;
import org.apache.log4j.*;

/**
 * Action of viewing employees
 * @author Admin
 */
public class ViewEmpsAction implements IAction {

    private static Logger logger = Logger.getLogger(ViewEmpsAction.class);
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws ModelException {
        HttpSession session = request.getSession();
        session.setAttribute(Constants.EMP_LIST_PLUS, DAOFactory.getModel(ModelType.Local).getEmpList());
        session.setAttribute(Constants.EMP_LIST, DAOFactory.getModel(ModelType.Local).getEmpList());
        session.setAttribute(Constants.DEPT_LIST, DAOFactory.getModel(ModelType.Local).getDeptList());
        session.removeAttribute(Constants.PARENT_LIST);
        return Constants.REDIRECT_EMP_TABLE;
    }
}
