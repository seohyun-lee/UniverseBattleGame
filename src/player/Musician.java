package player;


public class Musician extends Player {

	boolean DEFReady;
	public int ATKCount=3; //ù ������ Special ����
	
	public Musician() {}

	public Musician(String musician) {
		switch(musician) {
			//(musician) singer~drummer
			case "singer":
				this.name="Avril";
				this.setHP(1000);
				this.setATK(130);
				this.setDEF(40);
				this.setImgFile("singer.png");
				break;
			case "guitarist":
				this.name="Choi";
				this.setHP(1000);
				this.setATK(110);
				this.setDEF(80);
				this.setImgFile("guitarist.png");
				break;
			case "bassist":
				this.name="Katie";
				this.setHP(1000);
				this.setATK(100);
				this.setDEF(100);
				this.setImgFile("bassist.png");
				break;
			case "drummer":
				this.name="Josh";
				this.setHP(1000);
				this.setATK(120);
				this.setDEF(60);
				this.setImgFile("drummer.png");
				break;
			//�����Է��̶�� �����������-����ó��
			default:
				System.out.println("[ERROR] String�� ���� ����� �Էµ��� �ʾҽ��ϴ�.");
		}
	}

	public void attack(Monster target) {
		System.out.printf("%s�� %s�� �����մϴ�.\n", this.name, target.name);
		int tempATK = this.getATK();
		
		//���� ��� �� ���
		target.setHP(target.getHP()-tempATK);
		target.show();
		this.ATKCount++; //����Ƚ�� ����
		
		//target�� hp�� 0������ ���
		if(target.getHP()<=0) {
			target.setHP(0);
			target.show();
			System.out.println(target.name+"(��)�� ��������!");
		}		
	}
	
	public void defense() {
		System.out.printf(this.name + "�� ������ ����� �غ� �մϴ�.\n");
		this.DEFReady=true;
	}
	
	public void rest() {
		System.out.printf(this.name + "�� �޽��� ���� ü���� ȸ���մϴ�.\n");
		this.setHP(this.getHP()+this.getDEF()*2);
		this.show();
	}
	
	public void special(Monster target) {
		if(this.ATKCount>=3) {
			System.out.printf(this.name + "�� Ư�� ��ų�� ����մϴ�.\n");
			this.setATK(this.getATK()*4);
			attack(target);
			this.setATK(this.getATK()/4);
			this.ATKCount=0;
		}
		else {
			System.out.printf("[ERROR] ���� ��ų�� ����� �� ���µ� ����Ǿ����ϴ�.");
			System.out.println(ATKCount);
		}
	}

}
