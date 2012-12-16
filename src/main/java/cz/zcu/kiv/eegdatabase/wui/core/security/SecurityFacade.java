package cz.zcu.kiv.eegdatabase.wui.core.security;

public interface SecurityFacade {

    boolean authorization(String userName, String password);

    void logout();

    boolean personAbleToWriteIntoGroup(int researchGroupId);

    boolean userIsExperimenter();

    boolean isAdmin();

    boolean userIsGroupAdmin();

    boolean userIsOwnerOrCoexperimenter(int experimentId);

    boolean userCanViewPersonDetails(int personId);

    boolean userIsOwnerOfScenario(int scenarioId);

    boolean userIsOwnerOrCoexpOfCorrespExperiment(int fileId);

    boolean userIsExperimenterInGroup(int groupId);

    boolean userIsAdminInGroup(int groupId);

    boolean userIsMemberInGroup(int groupId);

    boolean userCanEditPerson(int personId);

    boolean isAuthorizedToRequestGroupRole();

}