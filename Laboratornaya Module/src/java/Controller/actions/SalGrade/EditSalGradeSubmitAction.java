package Controller.actions.SalGrade;

import Model.SalGradeStorage.SalGrade;
import Controller.actions.ActionDispatcher.IAction;
import Model.DAO.*;
import javax.servlet.http.*;
import Model.DAO.DAOFactory;
import org.apache.commons.lang3.*;

/**
 * Submitting of editing salary grade
 * @author sikorskyi
 */
public class EditSalGradeSubmitAction implements IAction {

    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws ModelException {
        HttpSession session = request.getSession();
        if (StringUtils.isEmpty(request.getParameter(Constants.GRADE))) {
            SalGrade salGrade = createSalGradeWithoutGrade(request);
            DAOFactory.getModel(ModelType.Local).addSalGrade(salGrade);
            session.removeAttribute(Constants.SALGRADE);
            session.setAttribute(Constants.SALGRADE_LIST, DAOFactory.getModel(ModelType.Local).getSalGradeList());
        } else {
            SalGrade salGrade = createSalGrade(request);
            DAOFactory.getModel(ModelType.Local).editSalGrade(salGrade);
            session.removeAttribute(Constants.SALGRADE);
            session.setAttribute(Constants.SALGRADE_LIST, DAOFactory.getModel(ModelType.Local).getSalGradeList());
        }
        return Constants.REDIRECT_SALGRADE_TABLE;
    }

    private SalGrade createSalGrade(HttpServletRequest request) {
        int grade = Integer.valueOf(request.getParameter(Constants.GRADE));
        double minSal = Double.valueOf(request.getParameter(Constants.MINSAL));
        double maxSal = Double.valueOf(request.getParameter(Constants.MAXSAL));
        SalGrade salGrade = new SalGrade(grade, minSal, maxSal);
        return salGrade;
    }

    private SalGrade createSalGradeWithoutGrade(HttpServletRequest request) {
        SalGrade salGrade = new SalGrade();
        salGrade.setMinSal(Double.valueOf(request.getParameter(Constants.MINSAL)));
        salGrade.setMaxSal(Double.valueOf(request.getParameter(Constants.MAXSAL)));
        return salGrade;
    }
}
