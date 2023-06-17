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
			//�����Է��̶�� �����������-����ó��
			default:
				System.out.println("[ERROR] String�� ���� ����� �Էµ��� �ʾҽ��ϴ�.");
		}
	}

	public void attack(Musician target) {
		System.out.printf("%s�� %s�� �����մϴ�.\n", this.name, target.name);
		int tempATK = this.getATK();
		
		//target(->Musician)�� ���� �Ǻ�
		if(target.DEFReady==true) {
			tempATK-=target.getDEF();
		}
		
		//���� ��� �� ���
		target.setHP(target.getHP()-tempATK);
		target.show();
		
		//�������
		if(target.DEFReady==true) {
			target.DEFReady=false;
		}
		
		//target�� hp�� 0������ ���
		if(target.getHP()<=0) {
			target.setHP(0);
			target.show();
			System.out.println(target.name+"(��)�� ��������!");
		}		
	}
	
}
