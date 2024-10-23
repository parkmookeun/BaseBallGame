package sparta.baseball.level3;

public enum GameMenu {
    START(1),
    RECORD(2),
    EXIT(3);

    private final int menu;

    GameMenu(int menu){
        this.menu = menu;
    }
    public int getMenu(){
        return menu;
    }

    public static GameMenu fromValue(int selectedMenu){
        for(GameMenu gameMenu : GameMenu.values()){
            if(gameMenu.getMenu() == selectedMenu){
                return gameMenu;
            }
        }
        throw new IllegalArgumentException("1번 2번 3번 메뉴 중 하나를 골라주세요!");
    }
}
