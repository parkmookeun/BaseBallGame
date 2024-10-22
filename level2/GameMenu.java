package sparta.baseball.level2;

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
        throw new IllegalArgumentException("잘못된 메뉴입니다.");
    }
}
