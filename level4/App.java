package sparta.baseball.level4;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int defaultMode = 3;
        int selected = 0;

        BaseballGame baseballGame = new BaseballGame(defaultMode);
            while(true) {
                baseballGame.showMenu();
                try{
                    selected = scanner.nextInt();
                    scanner.nextLine();

                    switch (GameMenu.fromValue(selected)) {
                        case MODE:
                            gameSet(scanner, baseballGame);
                            gameStart(baseballGame, scanner);
                            break;
                        case START:
                            gameStart(baseballGame,scanner);
                            break;

                        case RECORD:
                            baseballGame.showRecords();
                            break;

                        case EXIT:
                            //게임 탈출하기
                            System.out.println("게임을 종료합니다 . . .");
                            return;
                    }
                }catch (RuntimeException exception){
                    System.out.println("입력에 오류가 있습니다.");
                    scanner.reset();
                }
            }
    }

    /**
     *
     * @param scanner 입력객체
     * @param baseballGame 숫자야구게임 객체
     */
    private static void gameSet(Scanner scanner, BaseballGame baseballGame) {
        System.out.println("설정하고자 하는 자리수를 입력하세요.");
        int mode = scanner.nextInt();
        scanner.nextLine();
        baseballGame.setMode(mode);

        System.out.println(mode+"자리수 난이도로 설정되었습니다.");
    }


    /**
     *
     * @param baseballGame 숫자야구게임 객체
     * @param scanner 입력객체
     */
    private static void gameStart(BaseballGame baseballGame,Scanner scanner) {
        String inputList = "";
        int count = 0;

        baseballGame.initGame();

        //확인을 위해 정답 숫자 출력해보기
        baseballGame.showResult();

        System.out.println();
        System.out.println(baseballGame.getDigit() + "개의 숫자를 입력하세요!!");
        //게임 핵심 로직 시작!
        do {
            baseballGame.initTrial();

            inputList = typeNumber(scanner);
            count++;

        } while (!baseballGame.checkResult(inputList,count)); // 결과확인: 올 스트라이크면 탈출 아니면 반복!
    }

    /**
     *
     * @param scanner 입력객체
     * @return 입력한 숫자
     */
    private static String typeNumber(Scanner scanner) {
        String input = scanner.nextLine();
        //만약 입력에 숫자 이외가 있다면
        boolean isNum = input.matches("^[0-9]+$");
        if(!isNum){
            throw new RuntimeException("숫자만 입력 가능합니다!");
        }
        //만약 숫자에 중복이 있다면
        Set<Character> numList = new HashSet<>();
        for(int i=0; i<input.length(); i++){
            boolean notDupl = numList.add(input.charAt(i));
            if(!notDupl){
                throw new InputDuplException("입력한 숫자에 중복이 있습니다!");
            }
        }
        return input;
    }
}
