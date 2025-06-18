package pattern.memento;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 11:03
 */
public class Test1 {
    public static void main(String[] args) {
        StateRepository stateRepository = new StateRepository();
        StateManager stateManager = new StateManager();

        stateManager.setState("击杀小BOSS");
        StateSaver stateSaver1 = stateManager.saveState(stateManager.getState());
        stateRepository.saveState(stateSaver1);

        stateManager.setState("击杀大BOSS");
        StateSaver stateSaver2 = stateManager.saveState(stateManager.getState());
        stateRepository.saveState(stateSaver2);

        stateManager.setState("第一关通关");
        StateSaver stateSaver3 = stateManager.saveState(stateManager.getState());
        stateRepository.saveState(stateSaver3);

        System.out.println(stateRepository.getStates());
        System.out.println(stateManager.getState());

        stateManager.restoreState(stateRepository.getState(1));
        System.out.println(stateManager.getState());
    }
}
