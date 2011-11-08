
package Controller.actions.Emp;

import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import Model.DAO.*;
import org.apache.commons.lang3.*;

/**
 * Action of submitting employees` search
 * @author sikorskyi
 */
public class FindEmpSubmit implements IAction {

    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws ModelException {
        HttpSession session = request.getSession();
        String ename = request.getParameter(Constants.ENAME);
        String salString = request.getParameter(Constants.SAL);
        if (StringUtils.isEmpty(salString) && (StringUtils.isEmpty(ename))) {
            throw new ModelException("You do not enter any data");
        } else if ((!StringUtils.isEmpty(ename)) && (!StringUtils.isEmpty(salString))) {
            session.setAttribute(Constants.EMP_LIST, DAOFactory.getModel(ModelType.Local).findEmps(ename, Integer.valueOf(salString)));
        } else if ((!StringUtils.isEmpty(ename)) && (StringUtils.isEmpty(salString))) {
            session.setAttribute(Constants.EMP_LIST, DAOFactory.getModel(ModelType.Local).findEmpsByEname(ename));
        } else if ((StringUtils.isEmpty(ename)) && (!StringUtils.isEmpty(salString))) {
            session.setAttribute(Constants.EMP_LIST, DAOFactory.getModel(ModelType.Local).findEmpsBySal(Integer.valueOf(salString)));
        }
        return Constants.REDIRECT_EMP_TABLE;
    }
}
