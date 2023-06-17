package player;


public class Player {
	public String name;
	private int HP;
	private int ATK;
	private int DEF;
	private String imgFile; // 이미지 파일 이름을 저장할 문자열

	public Player() {}
	
	public void show() {
		System.out.printf("%20s : %10d  %10d\n", this.name, this.HP, this.ATK);
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hp) {
		this.HP = hp;
	}

	public int getATK() {
		return ATK;
	}

	public void setATK(int atk) {
		this.ATK = atk;
	}

	public String getImgFile() {
		return this.imgFile;
	}

	public void setImgFile(String imgFile) {
		this.imgFile = "./src/images/" + imgFile;
	}

	public int getDEF() {
		return DEF;
	}

	public void setDEF(int def) {
		DEF = def;
	}

}
