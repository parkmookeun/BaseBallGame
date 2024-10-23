package sparta.baseball.level4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseballGame {
    private int digit;    // 정답 자릿수
    private int strike; //스트라이크 개수
    private int ball;   //볼 개수
    private int out;    //아웃 개수
    private List<Integer> randomList; //랜덤 숫자 리스트
    List<Integer> records;  //게임 당 시도 횟수 기록

    public static final int MIN_MODE = 3;   //최소 난이도
    public static final int MAX_MODE = 5;   //최대 난이도

    /**
     *
     * @param digit 자리수
     */
    public BaseballGame(int digit){
        this.digit = digit;
        this.records = new ArrayList<>();
    }

    // 자릿 수 리턴

    /**
     *
     * @return 자리수
     */
    public int getDigit() {
        return digit;
    }


    /**
     *
     * @param digit 자리수(난이도)
     */
    public void setMode(int digit){
        if(digit < MIN_MODE || digit > MAX_MODE){
            throw new SetModeException("3 ~ 5 중 선택하세요!");
        }
        this.digit = digit;
    }

    /**
     * @return 랜덤숫자 리스트
     */
    public List<Integer> getRandomList(){
        return randomList;
    }

    /**
     * 게임 초기화
     */
    public void initGame(){
        this.randomList = createRandomList(digit);
    }

    /**
     * 스트라이크/볼/아웃 초기화
     */
    public void initTrial(){
        this.strike = 0;
        this.ball = 0;
        this.out = 0;
    }

    /**
     * 메뉴 화면
     */
    public void showMenu(){
        System.out.println("환영합니다! 원하시는 번호를 입력해주세요");
        System.out.println("0. 자리수 설정 1. 게임 시작하기 2. 게임 기록 보기 3. 종료하기");
    }

    /**
     * 기록 화면
     */
    public void showRecords(){
        System.out.println();
        System.out.println("< 게임 기록 보기 >");

        for(int i=0; i<records.size(); i++){
            System.out.println((i+1)+"번째 게임 : 시도 횟수 - " +records.get(i));
        }
        System.out.println();
    }

    /**
     *
     * @param inputList 입력된 숫자
     * @param count 시도 횟수
     * @return 게임이 성공했는지 실패했는지
     */
    public boolean checkResult(String inputList, int count) {
        boolean isOut = true;   //아웃여부 변수
        //스트라이크 or 볼 or 아웃 인지 체크하는 로직이다.
        if(inputList.isEmpty()){
            System.out.println("입력 오류가 발생하여 다시 입력합니다 . . .");
            return false;
        }
        for(int i = 0; i< randomList.size(); i++){
            int inputNumber = Integer.parseInt(String.valueOf(inputList.charAt(i)));
            //스트라이크 확인
            if(randomList.get(i) == inputNumber){
                strike++;
                continue;
            }
            //볼 확인
            for (int number : randomList) {
                if (number == inputNumber) {
                    ball++;
                    //볼이므로 아웃 x!
                    isOut = false;
                    break;
                }
            }
            //아웃 확인
            if (isOut){
                out++;
            }
            isOut = true;
        }

        if(strike == digit){
            System.out.printf("%d strike!!! 축하드립니다!!!\n",digit);
            System.out.println();
//            System.out.println(digit + "strike!!! 축하드립니다!!!");

            showResult();
            records.add(count);
            showRecords();

            return true;
        }else {

            System.out.printf("%d strike! %d ball! %d out! 아쉽습니다 ㅠㅠ\n",strike,ball,out);
//            System.out.println(strike +"strike! " + ball +"ball! "+ out +"out 아쉽습니다 ㅠㅠ");
            return false;
        }
    }

    /**
     *
     * @param input 자리수
     * @return 랜덤 리스트
     */
    private List<Integer> createRandomList(int input) {
        List<Integer> selectedNumbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Collections.shuffle(selectedNumbers);

        return selectedNumbers.subList(0,input);
    }

    /**
     * 정답 보여주기
     */
    public void showResult(){
        System.out.print("정답 숫자: ");
        for (int number : randomList) {
            System.out.print(number);
        }
        System.out.println();
    }

}
