package com.BowlingGame;

import java.util.ArrayList;

public class BowlingGame {

	int gameScore=0;
	ArrayList<Integer> rolls = new ArrayList<Integer>();
	ArrayList<Integer> frameScores = new ArrayList<Integer>();
	int rollsCount=0;
	public int pinsDown;
	
	public void roll(int pinsDown) {
		rollsCount++;
		if(rollsCount<=21) {			
					pinsDown = updateRollsListToPinsDown(pinsDown);					
					updateFrameScoreListToCummulativeTotalScore();	
					addBonusifPreviousFrameWasASpare();
					addBonusIfPreviousFrameOr2FramesWasAStrike(pinsDown);							
		}
	}

	public int updateRollsListToPinsDown(int pinsDown) {
		rolls.add(pinsDown);
		if(rollsCount<21) {		
		gameScore+=pinsDown;
		}
		
		if(rollsCount==21 &&(getPinsDownInPrevXRoll(1)+getPinsDownInPrevXRoll(2)>=10) ) {			
			gameScore+=pinsDown;
			}
		
		if(pinsDown==10 && rollsCount%2!=0 && rollsCount<=18) {
			rolls.add(0);
			rollsCount++;
			pinsDown=0;	}
		return pinsDown;
	}
	
	public void updateFrameScoreListToCummulativeTotalScore() {
		if(rollsCount%2==0 && rollsCount<=21) { 
			  frameScores.add(gameScore); 
			  }
	}

	public void addBonusIfPreviousFrameOr2FramesWasAStrike(int pinsDown) {
		boolean previousFrameWasAStrike = rolls.size()>3 && frameScores.size()>1 && (getPinsDownInPrevXRoll(3) == 10) && rollsCount%2==0;
		boolean previous2FramesWereStrikes = rollsCount%2==0 && frameScores.size()>2 && rolls.size()>5 && (getPinsDownInPrevXRoll(3) == 10)
				&& (getPinsDownInPrevXRoll(5) == 10);
		
		if(previousFrameWasAStrike) { //1Strike
			int bonus = pinsDown+getPinsDownInPrevXRoll(1);
			gameScore+=bonus;	
			addBonusToPreviousXFrame(1, bonus);
			addBonusToPreviousXFrame(0, bonus);
		}
		
		if (previous2FramesWereStrikes){ //Consecutively 2 Strikes
			int bonus = getPinsDownInPrevXRoll(1);
			gameScore+=bonus;	
			addBonusToPreviousXFrame(2, bonus);
			addBonusToPreviousXFrame(1, bonus);
			addBonusToPreviousXFrame(0, bonus);
		}
	}

	public void addBonusifPreviousFrameWasASpare() {		
		boolean previousFrameWasASpare = rolls.size()>3 && frameScores.size()>1  && (rolls.get(rolls.size()-3) + getPinsDownInPrevXRoll(3) ==10)
				&& rollsCount%2==0 && getPinsDownInPrevXRoll(3)!=10;		
		if(previousFrameWasASpare) {//Spare
			int bonus = getPinsDownInPrevXRoll(1);
			gameScore+=bonus;
			addBonusToPreviousXFrame(1, bonus);
			addBonusToPreviousXFrame(0, bonus);
		}
	}	
	
	public void addBonusToPreviousXFrame(int howManyFramesBack, int bonus) {
		int frameIndex = frameScores.size()-1;
		frameScores.set(frameIndex-howManyFramesBack, getPreviousXFrameScore(howManyFramesBack)+bonus);
	}
	
	public int getPinsDownInPrevXRoll(int howManyRollsBack) {
		int rollIndex = rolls.size()-1;
		return rolls.get(rollIndex - howManyRollsBack);
	}
	
	public Integer getPreviousXFrameScore(int howManyFramesBack) {
		int frameIndex = frameScores.size()-1;
		return frameScores.get(frameIndex- howManyFramesBack);
	}
	
	public int gameScore() {
		return gameScore;
	}

	public int getFrameScores(int i) {
		return frameScores.get(i);
	}

}
