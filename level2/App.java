package sparta.baseball.level2;

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

        int input = 3;  //자리 수 입력(level2에선 3으로 고정)
        String inputList = ""; //사용자가 입력한 숫자(현재 3자리 숫자 입력)

        BaseballGame baseballGame = new BaseballGame(input); // BaseballGame 인스턴스 생성

        baseballGame.showMenu(); //게임 메뉴 보여주기

        int selected = scanner.nextInt(); //선택한 메뉴 1:START(게임 시작) 2:RECORD(기록보여주기 level2에선 x) 3:EXIT(종료)
        scanner.nextLine(); //입력 버퍼에 남은 값 없애기

        //선택한 게임 메뉴에 따라 실행
        switch(GameMenu.fromValue(selected)){
            case START:
                //확인을 위해 정답 숫자 출력해보기
                for (int i : baseballGame.getRandomList()) {
                    System.out.print(i);
                }
                System.out.println();
                System.out.println(input+"개의 숫자를 입력하세요!!");
                //게임 핵심 로직 시작!
                do {
                    baseballGame.setTrial();

                    try {
                        inputList = typeNumber(scanner);
                    }// 문자 입력 또는 숫자 중복 입력 시 예외 처리
                    catch (InputMismatchException | InputDuplException exception) {
                        System.out.println(exception.getMessage());
                        return;
                    }
                } while (!baseballGame.checkResult(inputList)); // 결과확인: 올 스트라이크면 탈출 아니면 반복!
                break;

            case RECORD:
                //기록보기 아직은 x!
                break;

            case EXIT:
                //게임 탈출하기
                System.out.println("게임을 종료합니다 . . .");
                break;
        }
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
