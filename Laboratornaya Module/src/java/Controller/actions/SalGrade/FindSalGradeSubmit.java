
package Controller.actions.SalGrade;

import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import Model.DAO.*;
import org.apache.commons.lang3.*;
/**
 * Action of searching submit
 * @author sikorskyi
 */
public class FindSalGradeSubmit implements IAction {
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws ModelException {
        String stringGrade = request.getParameter(Constants.GRADE);
         if (StringUtils.isEmpty(stringGrade)) {
            throw new ModelException("You do not enter any data");
        } else if (!StringUtils.isEmpty(stringGrade)) {
            request.getSession().setAttribute(Constants.SALGRADE_LIST, DAOFactory.getModel(ModelType.Local).findSalGradesByGrade(Integer.valueOf(stringGrade)));
        }
        return Constants.REDIRECT_SALGRADE_TABLE;
    }
}
