package sparta.baseball.level3;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class BaseballGame {

    private final int digit;    // 정답 자릿수
    private int strike; //스트라이크 개수
    private int ball;   //볼 개수
    private int out;    //아웃 개수
    private int[] randomList; //랜덤 숫자 리스트
    List<Integer> records;  //게임 당 시도 횟수 기록

    /*
        public BaseballGame(int digit)
        -> 정답 자릿수와 기록 리스트를 초기화하고, 랜덤 숫자 생성 함수를 호출한다.
        @param : digit - 정답 자릿수
     */
    public BaseballGame(int digit){
        this.digit = digit;
        this.records = new ArrayList<>();
    }

    // 자릿 수 리턴
    public int getDigit() {
        return digit;
    }

    //랜덤 숫자 리스트 반환
    public int[] getRandomList(){
        return randomList;
    }

    /*
        public void setGame()
         -> 한 게임 당 랜덤 숫자 다시 생성
     */
    public void setGame(){
        this.randomList = createRandomList(digit);
    }

    /*
        public void setTrial()
        -> 시도를 할때마다 변수들을 다시 세팅한다. (스트라이크/볼/아웃)
     */
    public void setTrial(){
        this.strike = 0;
        this.ball = 0;
        this.out = 0;
    }

    /*
        public void showMenu()
        -> 숫자 야구 게임의 메뉴를 보여준다.
     */
    public void showMenu(){
        System.out.println("환영합니다! 원하시는 번호를 입력해주세요");
        System.out.println("1. 게임 시작하기 2. 게임 기록 보기 3. 종료하기");
    }

    public void showRecords(){
        System.out.println();
        System.out.println("< 게임 기록 보기 >");

        for(int i=0; i<records.size(); i++){
            System.out.println((i+1)+"번째 게임 : 시도 횟수 - " +records.get(i));
        }
        System.out.println();
    }

    /*
        public boolean checkResult(String inputList)
        -> 입력된 숫자들에 대해 결과(스트라이크,볼,아웃)를 연산하고 보여준다.
        @param : inputList - 입력된 숫자
     */
    public boolean checkResult(String inputList, int count) {
        boolean isOut = true;   //아웃여부 변수
        //스트라이크 or 볼 or 아웃 인지 체크하는 로직이다.
        for(int i = 0; i< randomList.length; i++){
            int inputNumber = Integer.parseInt(String.valueOf(inputList.charAt(i)));
            //스트라이크 확인
            if(randomList[i] == inputNumber){
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
        }
        //결과 출력
        if(strike == digit){  //정답
            System.out.println(digit + "strike!!! 축하드립니다!!!");
            //정답 숫자 출력!
            showResult();
            records.add(count);
            showRecords();
            return true;
        }else { //오답
            System.out.println(strike +"strike! " + ball +"ball! "+ out +"out 아쉽습니다 ㅠㅠ");
            return false;
        }
    }

    /*
        private int[] createRandomList(int input)
        -> 랜덤으로 자릿수 만큼 숫자를 생성한다.
        @param : input - 자릿수 입력
        @returns : int[] selectedNumbers - 랜덤 리스트를 int 배열로 반환.
     */
    private int[] createRandomList(int input) {
        LinkedHashSet<Integer> selectedNumbers = new LinkedHashSet<>();
        int randomNum;

        while(selectedNumbers.size()<input){
            randomNum = (int) (Math.random() * 9) + 1;
            selectedNumbers.add(randomNum);
        }

        return selectedNumbers.stream().mapToInt(i->i).toArray();
    }

    /*
        private void showResult()
         -> 정답 숫자를 보여준다.
     */
    private void showResult(){
        System.out.print("정답 숫자: ");
        for (int number : randomList) {
            System.out.print(number);
        }
        System.out.println();
    }

}
