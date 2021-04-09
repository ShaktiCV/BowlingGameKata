package com.BowlingGameTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.BowlingGame.BowlingGame;

public class BowlingGameTest {
	
	BowlingGame game = new BowlingGame();
	
	@Test
	public void test_gutterBalls() {
		for(int i =0; i<20;i++) {
			game.roll(0);			
		}
		assertEquals(0, game.gameScore());
	}
	
	@Test
	public void test_TotalScoreConsidersOnly20RollsIfLastFrameIsNotStrikeOrSpare() {
		for(int i =0; i<24;i++) {
			game.roll(3);			
		}
		assertEquals(60, game.gameScore());
	}
	
	
	@Test
	public void test_SpareFrame() {
		game.roll(6);
		game.roll(4);
		game.roll(3);
		game.roll(2);
		assertEquals(18, game.gameScore());
	}
	
	@Test
	public void test_StrikeFrame() {
		game.roll(2);
		game.roll(3);
		game.roll(10);
		game.roll(2);
		game.roll(4);
		assertEquals(27, game.gameScore());
	}
	
	@Test
	public void test_1Spare_2ConsecStrikes() {
		game.roll(2);
		game.roll(3);
		game.roll(10);
		game.roll(10);
		game.roll(6);
		game.roll(4);
		for(int i =0; i<12;i++) {
			game.roll(3);			
		}
		assertEquals(100, game.gameScore());
	}
	
	@Test
	public void test_AllStrikes() {
		
		for(int i =0; i<12;i++) {
			game.roll(10);			
		}
		assertEquals(300, game.gameScore());
	}
	
	@Test
	public void test_AllSpares() {
		
		for(int i =0; i<10;i++) {
			game.roll(6);	
			game.roll(4);	
		}
		game.roll(10);
		assertEquals(164, game.gameScore());
	}
	
	@Test
	public void test_WhereSpareIsConsideredOnlyWhenScoreOf2RollsIs10WithinSameFrame() {
		game.roll(4);
		game.roll(3);
		game.roll(7);
		game.roll(1);
		game.roll(6);
		game.roll(4);
		for(int i =0; i<14;i++) {
			game.roll(3);			
		}
		assertEquals(70, game.gameScore());
	}
	
	@Test
	public void test_Where1stRollIs0AndSecondIs10PinsDownWhichIsSpare() {
		game.roll(5);
		game.roll(5);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(0);
		game.roll(10);
		game.roll(10);
		game.roll(4);
		game.roll(6);
		game.roll(4);
		game.roll(4);
		game.roll(1);
		game.roll(2);
		assertEquals(185, game.gameScore());
	}
	
	@Test
	public void test_FrameScores_After3Strikes() {
		game.roll(5);
		game.roll(5);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(0);
		game.roll(10);
		game.roll(10);
		game.roll(4);
		game.roll(6);
		game.roll(4);
		game.roll(4);
		game.roll(1);
		game.roll(2);
		assertEquals(80, game.getFrameScores(2));
	}
	
	@Test
	public void test_LastFrameHasStrikes() {
		for(int i =0; i<18;i++) {
			game.roll(3);			
		}
		game.roll(10);
		game.roll(10);
		game.roll(10);
		assertEquals(84, game.gameScore());
	}
	
	@Test
	public void test_LastFrameHasASpare() {
		for(int i =0; i<18;i++) {
			game.roll(3);			
		}
		game.roll(6);
		game.roll(4);
		game.roll(2);
		assertEquals(66, game.gameScore());
	}
	




}
