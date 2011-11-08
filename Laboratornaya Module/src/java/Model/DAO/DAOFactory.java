/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

/**
 *
 * @author Admin
 */
public class DAOFactory {

    private static IModel model = null;

    /**
     * 
     * @param type - type of Model to chose
     * @return iModel object
     * @throws ModelException
     */
    public static IModel getModel(ModelType type) throws ModelException {
        switch (type) {
            case Local:
                if (model == null) {
                    model = new DBModel();
                }
                return model;
            default:
                throw new ModelException("not found model type");
        }

    }
}
