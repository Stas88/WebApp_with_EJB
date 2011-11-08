/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.actions.SalGrade;

import Controller.actions.ActionDispatcher.IAction;
import javax.servlet.http.*;
import Model.DAO.*;
/**
 * Submit of action of viewing Salary Grade
 * @author sikorskyi
 */
public class ViewSalGradeAction implements IAction  {
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws ModelException {
        request.getSession().setAttribute(Constants.SALGRADE_LIST, DAOFactory.getModel(ModelType.Local).getSalGradeList());
        return Constants.REDIRECT_SALGRADE_TABLE;
    }
}
