import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class 닉네임변경 {

    String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};

    @Test
    public void solution(){
        solution(record);
    }

    public String[] solution(String[] record) {

        Map<String, String> userNickname = new HashMap<>();
        List<UserAction> actionS = new ArrayList<>();

        for (String r : record) {
            String[] splitR = r.split(" ");

            if (splitR[0].equals("Leave")) {

                actionS.add(new UserAction("나갔습니다.", splitR[1]));

            } else if (splitR[0].equals("Change")) {

                userNickname.put(splitR[1], splitR[2]);

            } else {

                userNickname.put(splitR[1], splitR[2]);
                actionS.add(new UserAction("들어왔습니다.", splitR[1]));
            }

        }

        List<String> arrayList = actionS.stream().map( a-> concatAnswerString(userNickname.get(a.getUserId()), a.getAction())).collect(Collectors.toList());

        String[] answer = arrayList.toArray(new String[arrayList.size()]);

        return answer;
    }

    public String concatAnswerString(String userId, String action){

        return userId + "님이 " + action;
    }

    public static class UserAction {
        String action;
        String userId;

        public UserAction(final String action, final String userId) {
            this.action = action;
            this.userId = userId;
        }


        public String getAction() {
            return action;
        }

        public String getUserId() {
            return userId;
        }

    }

}
