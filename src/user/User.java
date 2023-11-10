package user;

/**
 * @author Tobias Heidlund
 */
@SuppressWarnings("unused")
public abstract class User {
    public final boolean createPerm = false;
    public final boolean readPerm = false;
    public final boolean updatePerm = false;
    public final boolean deletePerm = false;

    public boolean canRead(){
        return readPerm;
    }
    public boolean canCreate(){
        return createPerm;
    }
    public boolean canUpdate(){
        return updatePerm;
    }
    public boolean canDelete(){
        return deletePerm;
    }
}
