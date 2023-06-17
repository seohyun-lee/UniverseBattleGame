package player;


public class Monster extends Player {
	
	public Monster() {}

	public Monster(String monster) {
		switch(monster) {
			//(monster) monster1~monster4
			case "monster1":
				this.name="Lava Monster";
				this.setHP(1000);
				this.setATK(100);
				this.setImgFile("monster_1.png");
				break;
			case "monster2":
				this.name="Stone Golem";
				this.setHP(1000);
				this.setATK(140);
				this.setImgFile("monster_2.png");
				break;
			case "monster3":
				this.name="Ice Dragon";
				this.setHP(1000);
				this.setATK(200);
				this.setImgFile("monster_3.png");
				break;
			case "monster4":
				this.name="Red Legend";
				this.setHP(1000);
				this.setATK(280);
				this.setImgFile("monster_4.png");
				break;
			//정상입력이라면 실행되지않을-오류처리
			default:
				System.out.println("[ERROR] String의 값이 제대로 입력되지 않았습니다.");
		}
	}

	public void attack(Musician target) {
		System.out.printf("%s가 %s를 공격합니다.\n", this.name, target.name);
		int tempATK = this.getATK();
		
		//target(->Musician)의 방어여부 판별
		if(target.DEFReady==true) {
			tempATK-=target.getDEF();
		}
		
		//공격 계산 및 출력
		target.setHP(target.getHP()-tempATK);
		target.show();
		
		//원래대로
		if(target.DEFReady==true) {
			target.DEFReady=false;
		}
		
		//target의 hp가 0이하인 경우
		if(target.getHP()<=0) {
			target.setHP(0);
			target.show();
			System.out.println(target.name+"(이)가 쓰러졌다!");
		}		
	}
	
}
