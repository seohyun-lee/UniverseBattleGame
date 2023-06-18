package player;


public class Musician extends Player {

	boolean DEFReady;
	public int ATKCount=3; //첫 공격은 Special 가능
	
	public Musician() {}

	public Musician(String musician) {
		switch(musician) {
			//musician: singer~drummer
			case "singer":
				this.name="Avril";
				this.setHP(1000);
				this.setATK(100);
				this.setDEF(70);
				this.setImgFile("singer.png");
				break;
			case "guitarist":
				this.name="Choi";
				this.setHP(1000);
				this.setATK(80);
				this.setDEF(90);
				this.setImgFile("guitarist.png");
				break;
			case "bassist":
				this.name="Katie";
				this.setHP(1000);
				this.setATK(90);
				this.setDEF(80);
				this.setImgFile("bassist.png");
				break;
			case "drummer":
				this.name="Josh";
				this.setHP(1000);
				this.setATK(70);
				this.setDEF(100);
				this.setImgFile("drummer.png");
				break;
			//오류처리-정상입력이라면 실행되지않음
			default:
				System.out.println("[ERROR] String의 값이 제대로 입력되지 않았습니다.");
		}
	}

	public void attack(Monster target) {
		System.out.printf("%s가 %s를 공격합니다.\n", this.name, target.name);
		int tempATK = this.getATK();
		
		tempATK-=target.getDEF();		
		//공격 계산
		target.setHP(target.getHP()-tempATK);
		
		//target의 hp가 0이하인 경우
		if(target.getHP()<=0) {
			target.setHP(0);
			target.show();
			System.out.println(target.name+"(이)가 쓰러졌다!");
		} else {
			this.ATKCount++; //공격횟수 세기
			target.show();
		}
	}
	
	public void defense() {
		System.out.printf(this.name + "이 공격을 방어할 준비를 합니다.\n");
		this.DEFReady=true;
	}
	
	public void rest() {
		System.out.printf(this.name + "이 휴식을 취해 체력을 회복합니다.\n");
		int tempHP = this.getHP()+this.getDEF()*4;
		if(tempHP>1000) tempHP=1000;
		this.setHP(tempHP);
		this.show();
	}
	
	public void special(Monster target) {
		if(this.ATKCount>=3) {
			System.out.printf(this.name + "이 특별한 힘을 사용합니다.\n");
			this.setATK(this.getATK()*4);
			attack(target);
			System.out.printf("아주 강한 공격! "+target.name + "에게 상당한 피해를 입혔습니다.\n");
			this.setATK(this.getATK()/4);
			this.ATKCount=0;
		}
		else {
			System.out.printf("[ERROR] 아직 스킬을 사용할 수 없는데 실행되었습니다.");
			System.out.println(ATKCount);
		}
	}

}
