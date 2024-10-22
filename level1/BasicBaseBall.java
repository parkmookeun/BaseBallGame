package sparta.baseball.level1;

import java.util.*;

public class BasicBaseBall {
    public static void main(String[] args) {
        //숫자 생성
        //서로 다른 3개의 숫자 생성
        //각 자리는 1-9까지 0은 x
        //동일한 숫자 사용 x 숫자 중복 x
        Scanner scanner = new Scanner(System.in);
        int input = 3;
        int strike;
        int ball;
        int out;
        String inputList = "";

        int[] randomList = createRandomList(input);

        //로직을 위해 랜덤 숫자 출력해보기
        for (int i : randomList) {
            System.out.print(i);
        }
        System.out.println();


        System.out.println(input+"개의 숫자를 입력하세요!!");

        //반복문 시작!
        while(true){
            strike = 0;
            ball = 0;
            out = 0;

            //사용자 입력
            try{
                inputList = typeNumber(scanner);
            }catch (InputMismatchException | InputDuplException exception){
                System.out.println(exception.getMessage());
                return;
            }

            //비교
            // ?스트라이크 ?볼인지 ?아웃인지 확인한다.
            if(checkResult(randomList, inputList, strike, ball, out, input)) break;

        }
    }

    private static boolean checkResult(int[] randomList, String inputList, int strike, int ball, int out, int input) {
        boolean isOut = true;
        for(int i = 0; i< randomList.length; i++){
            int inputNumber = Integer.parseInt(String.valueOf(inputList.charAt(i)));
            if(randomList[i] == inputNumber){
                strike++;
                continue;
            }

            for (int number : randomList) {
                if (number == inputNumber) {
                    ball++;
                    isOut = false;
                    break;
                }
            }

            if (isOut){
                out++;
            }
        }

        //결과 출력
        if(strike == input){
            System.out.println(input + "strike!!! 축하드립니다!!!");
            return true;
        }else {
            System.out.println(strike +"strike! " + ball +"ball! "+ out +"out 아쉽습니다 ㅠㅠ");
            return false;
        }
    }

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

    private static int[] createRandomList(int input) {
        LinkedHashSet<Integer> selectedNumbers = new LinkedHashSet<>();

        int randomNum;

        while(selectedNumbers.size()<input){
            randomNum = (int) (Math.random() * 9) + 1;
            selectedNumbers.add(randomNum);
        }

        return selectedNumbers.stream().mapToInt(i->i).toArray();
    }
}
