package sparta.baseball.level3;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
/*
    App 클래스
    BaseballGame 인스턴스를 사용해서 게임을 실행하는 메인 클래스이다.
 */
public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int input = 3;  //자리 수 입력(level3에선 3으로 고정)
        int selected = 0; //선택한 메뉴

        BaseballGame baseballGame = new BaseballGame(input); // BaseballGame 인스턴스 생성
        while(true) {
            baseballGame.showMenu(); //게임 메뉴 보여주기

            selected = selectMenu(scanner);   //메뉴 선택하기

            //선택한 게임 메뉴에 따라 실행
            switch (isValidMenu(selected)) {
                case START:
                    gameStart(baseballGame, input, scanner);
                    break;

                case RECORD:
                    baseballGame.showRecords();
                    break;

                case EXIT:
                    //게임 탈출하기
                    System.out.println("게임을 종료합니다 . . .");
                    return;
            }
        }
    }

    /*
        private static int selectMenu(int selected, Scanner scanner)
         -> 숫자 야구 게임의 메뉴를 선택한다.
         @param :
            int selected
     */
    private static int selectMenu(Scanner scanner) {
        int select = 0; //선택한 메뉴
        try {
            select = scanner.nextInt(); //선택한 메뉴 1:START(게임 시작) 2:RECORD(기록보여주기 level2에선 x) 3:EXIT(종료)
        }catch (RuntimeException exception){
            System.out.println("올바른 숫자를 입력해주세요!");
        }
        scanner.nextLine(); //입력 버퍼에 남은 값 없애기
        return select;
    }

    /*
        private static void gameStart(BaseballGame baseballGame, int input, Scanner scanner)
         -> 숫자 야구 게임의 게임 한번을 시행한다.
        @param :
            BaseballGame baseballGame -> 숫자 야구 게임을 위해 생성한 인스턴스
            int input -> 정답 숫자의 개수
            Scanner scanner -> 함수 안에서 입력을 위한 Scanner 인스턴스
     */
    private static void gameStart(BaseballGame baseballGame, int input, Scanner scanner) {
        String inputList = ""; //사용자가 입력한 숫자(현재 3자리 숫자 입력)
        int count = 0; //게임 당 시도 횟수 기록
        //게임 셋
        baseballGame.setGame();

        //확인을 위해 정답 숫자 출력해보기
        for (int i : baseballGame.getRandomList()) {
            System.out.print(i);
        }
        System.out.println();
        System.out.println(input + "개의 숫자를 입력하세요!!");
        //게임 핵심 로직 시작!
        do {
            baseballGame.setTrial();
            try {
                inputList = typeNumber(scanner);
            }// 문자 입력 또는 숫자 중복 입력 시 예외 처리
            catch (InputMismatchException | InputDuplException exception) {
                System.out.println(exception.getMessage());
            }
            //시도 횟수 + 1
            count++;
        } while (!baseballGame.checkResult(inputList,count)); // 결과확인: 올 스트라이크면 탈출 아니면 반복!
    }

    /*
        private static GameMenu isValidMenu(int selected)
         -> 입력한 메뉴가 올바른 메뉴인지 검증한다.
         @param:
            int selected -> 선택된 메뉴
     */
    private static GameMenu isValidMenu(int selected) {
        GameMenu selectedMenu = GameMenu.EXIT;
        try{
            selectedMenu = GameMenu.fromValue(selected);
        }catch (IllegalArgumentException exception){
            System.out.println(exception.getMessage());
        }
        return selectedMenu;
    }

    /*
        private static String typeNumber(Scanner scanner)

        사용자가 입력한 숫자를 예외처리함으로써, 올바른 숫자이면
        리턴한다.

        @param : scanner - Scanner 클래스 인스턴스
        @return : input - 올바른 숫자를 String 클래스 인스턴스로 반환
     */
    private static String typeNumber(Scanner scanner) {
        String input = scanner.nextLine();
        //만약 입력에 숫자 이외가 있다면
        boolean isNum = input.matches("^[0-9]+$");
        if(!isNum){
            throw new InputMismatchException("숫자만 입력 가능합니다!");
        }
        //만약 숫자에 중복이 있다면
        HashSet<Character> numList = new HashSet<>();
        for(int i=0; i<input.length(); i++){
            boolean notDupl = numList.add(input.charAt(i));
            if(!notDupl){
                throw new InputDuplException("입력한 숫자에 중복이 있습니다!");
            }
        }
        return input;
    }
}
