# Universe Battle Game
2023-1 객체지향프로그래밍 최종 과제물-Java Swing GUI로 Battle 게임 제작

## 1. 프로그램 목적
*Universe (Band) Battle Game!* 우리의 주인공은 지구 출신의 록 밴드인데, 특별한 초능력을 갖게 되어 우주를 수호하는 히어로로 활동하고 있다. 그들은 우주의 평화를 깨뜨리는 우주 괴물들과 싸워 이겨야 한다!
사용자는 Singer, Guitarist, Bassist, Drummer 중 하나의 Musician을 선택해 플레이한다.

## 2. 클래스 다이어그램
![ClassDiagram-최종](https://github.com/seohyun-lee/UniverseBattleGame/assets/32611398/94db9463-56eb-418e-9605-ad8c3fb8a2e3)

## 3. 동작 시나리오
(1)	시작 화면에서 'START 버튼'을 누르면 게임을 시작한다.

(2)	게임 플레이 화면의 아래에 배경음악의 볼륨을 슬라이더로 조절할 수 있는 Audio 창이 있다. (*창을 끌 경우 게임 전체가 종료된다)

(3)	플레이어 선택 화면에서는 플레이할 Musician을 선택하고, 'LET'S BATTLE 버튼'을 클릭해 배틀을 시작할 수 있다. Musician을 선택하지 않은 채로 LET'S BATTLE 버튼을 클릭하면 다음 화면으로 넘어가지지 않고, 버튼 아래에 플레이어를 선택하라는 경고 문구가 뜬다.

(4)	배틀 화면에서는 Musician과 Monster가 1:1로 전투를 진행한다.
* Musician과 Monster는 Player의 서브클래스들이다.
- Player는 필드로 name, HP, ATK, DEF, imgFile을 가지고 있다. 또한 메소드로 show()와 각각의 private 변수들에 대한 getter, setter들을 가지고 있다.
-	Musician은 Player로부터 상속받은 것 이외에 필드로 Defense 사용 여부를 확인하는 DEFReady와 공격 횟수를 세는 ATKCount를 가지고 있다. 또한 메소드로 attack(), defense(), rest(), special()을 가지고 있다.
-	Monster는 Player로부터 상속받은 것 이외에 추가의 필드를 갖고 있지 않고 메소드로 attack()을 가지고 있다.
* 4마리의 몬스터가 차례로 나오는데, 뒤로 갈수록 더 공격력과 방어력이 높은 몬스터가 나온다. 마지막 몬스터는 보스 몬스터라 배경음악도 다르다.
* 매 전투 시마다 Musician과 Monster의 HP는 최대치로 충전된다.

(5)	Musician은 자기 차례에 Attack, Defense, Rest, Special 중 1가지를 선택해 버튼을 클릭한다. Monster는 Attack만 가능하다.

*Musician*
-	Attack은 (Musician의 ATK - Monster의 DEF)만큼 Monster의 HP를 감소시킨다.
-	Defense는 다음에 공격받을 때 (Monster의 ATK - Musician의 DEF)만큼 공격받게 되도록 방어한다. (DEFReady의 값을 True로 바꾼다.)
-	Rest는 Musician의 HP를 DEF*4만큼 회복시킨다.
-	Special은 Attack공격의 4배의 공격력(ATK*4)으로 공격한다. Attack을 세번 쓸 때마다 Special을 새로 쓸 수 있다. (ATKCount로 카운트한다.)

*Monster*
- Monster의 Attack은 (Monster의 ATK)만큼 Musician의 HP를 감소시킨다. 만약 Musician의 DEFReady가 True인 경우에는 (Monster의 ATK - Musician의 DEF) 만큼 Musician의 HP를 감소시킨다.

(6)	Monster의 HP가 0이 되면 승리 화면이 뜨고 다음 배틀을 바로 이어하거나 시작 화면으로 돌아갈 수 있다. 시작 화면으로 돌아가도 몬스터를 처치한 정도가 초기화되진 않는다.

(7)	Musician의 HP가 0이 되면 게임 오버 화면이 뜬다. 시작 화면으로 돌아갈 수 있다. 몬스터를 처치한 정도가 초기화되진 않는다.

(8)	네 마리의 몬스터를 모두 처치하면 게임 클리어 화면이 뜨고 특별한 이미지가 뜬다. Reset으로 게임을 초기화할 수 있다.

## 4. 시연 영상
https://github.com/seohyun-lee/UniverseBattleGame/assets/32611398/fe1a388a-f83f-41d2-8375-6a734da983f1
