package edu.westga.cs.babble.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

/**
 * Tests for the TileBag#drawTile() method
 * 
 * @author Megan Brown
 *
 */
public class TestTileBagDrawTile {

	@Test
	public void canDrawAllTiles() {
		TileBag tileBag = new TileBag();
		for (int i = 0; i < 98; i++) {
			try {
				tileBag.drawTile();
			} catch (EmptyTileBagException e) {
				e.printStackTrace();
			}
		}
		assertTrue(tileBag.isEmpty());
	}

	@Test
	public void canNotDrawTooManyTiles() {
		assertThrows(EmptyTileBagException.class, () -> {
			TileBag tileBag = new TileBag();
			for (int i = 0; i < 99; i++) {
				tileBag.drawTile();
			}
		});
	}

	@Test
	public void hasProperTileDistribution() {
		TileBag tileBag = new TileBag();
		int E = 0;
		int A = 0;
		int I = 0;
		int O = 0;
		int N = 0;
		int R = 0;
		int T = 0;
		int L = 0;
		int S = 0;
		int U = 0;
		int D = 0;
		int G = 0;
		int B = 0;
		int C = 0;
		int M = 0;
		int P = 0;
		int F = 0;
		int H = 0;
		int V = 0;
		int W = 0;
		int Y = 0;
		int K = 0;
		int J = 0;
		int X = 0;
		int Q = 0;
		int Z = 0;
		int index = 0;
		while (index < 98) {
			try {
				Tile tile = tileBag.drawTile();
				if(tile.getLetter() == 'E') {
					E++;
				}
				if(tile.getLetter() == 'A') {
					A++;
				}
				if (tile.getLetter() == 'I') {
					I++;
				}
				if (tile.getLetter() == 'O') {
					O++;
				}
				if (tile.getLetter() == 'N') {
					N++;
				}
				if (tile.getLetter() == 'R') {
					R++;
				}
				if (tile.getLetter() == 'T') {
					T++;
				}
				if (tile.getLetter() == 'L') {
					L++;
				}
				if (tile.getLetter() == 'S') {
					S++;
				}
				if (tile.getLetter() == 'U') {
					U++;
				}
				if (tile.getLetter() == 'D') {
					D++;
				}
				if (tile.getLetter() == 'G') {
					G++;
				}
				if (tile.getLetter() == 'B') {
					B++;
				}
				if (tile.getLetter() == 'C') {
					C++;
				}
				if (tile.getLetter() == 'M') {
					M++;
				}
				if (tile.getLetter() == 'P') {
					P++;
				}
				if (tile.getLetter() == 'F') {
					F++;
				}
				if (tile.getLetter() == 'H') {
					H++;
				}
				if (tile.getLetter() == 'V') {
					V++;
				}
				if (tile.getLetter() == 'W') {
					W++;
				}
				if (tile.getLetter() == 'Y') {
					Y++;
				}
				if (tile.getLetter() == 'K') {
					K++;
				}
				if (tile.getLetter() == 'J') {
					J++;
				}
				if (tile.getLetter() == 'X') {
					X++;
				}
				if (tile.getLetter() == 'Q') {
					Q++;
				}
				if (tile.getLetter() == 'Z') {
					Z++;
				}
			} catch (EmptyTileBagException e1) {
				e1.printStackTrace();
			}
			index++;
		}
		
		assertEquals(12, E);
		assertEquals(9, A);
		assertEquals(9, I);
		assertEquals(8, O);
		assertEquals(6, N);
		assertEquals(6, R);
		assertEquals(6, T);
		assertEquals(4, L);
		assertEquals(4, S);
		assertEquals(4, U);
		assertEquals(4, D);
		assertEquals(3, G);
		assertEquals(2, B);
		assertEquals(2, C);
		assertEquals(2, M);
		assertEquals(2, P);
		assertEquals(2, F);
		assertEquals(2, H);
		assertEquals(2, V);
		assertEquals(2, W);
		assertEquals(2, Y);
		assertEquals(1, K);
		assertEquals(1, J);
		assertEquals(1, X);
		assertEquals(1, Q);
		assertEquals(1, Z);

	}
}
