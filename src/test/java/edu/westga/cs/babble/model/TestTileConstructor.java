package edu.westga.cs.babble.model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests for class Tile's constructor
 * 
 * @author Megan Brown
 * @version 8/28/18
 */
public class TestTileConstructor {

	@Test
	public void shouldNotAllowNonLetters() {
		assertThrows(IllegalArgumentException.class, () -> {
			Tile tile = new Tile('2');
			tile.getLetter();
		});
	}

	@Test
	public void shouldCreateOnePointTiles() {
		Tile a = new Tile('a');
		Tile e = new Tile('e');
		Tile i = new Tile('i');
		Tile o = new Tile('o');
		Tile n = new Tile('n');
		Tile r = new Tile('r');
		Tile t = new Tile('t');
		Tile l = new Tile('l');
		Tile s = new Tile('s');
		Tile u = new Tile('u');
		Tile A = new Tile('A');
		Tile E = new Tile('E');
		Tile I = new Tile('I');
		Tile O = new Tile('O');
		Tile N = new Tile('N');
		Tile R = new Tile('R');
		Tile T = new Tile('T');
		Tile L = new Tile('L');
		Tile S = new Tile('S');
		Tile U = new Tile('U');
		assertEquals(1, a.getPointValue());
		assertEquals(1, e.getPointValue());
		assertEquals(1, i.getPointValue());
		assertEquals(1, o.getPointValue());
		assertEquals(1, n.getPointValue());
		assertEquals(1, r.getPointValue());
		assertEquals(1, t.getPointValue());
		assertEquals(1, l.getPointValue());
		assertEquals(1, s.getPointValue());
		assertEquals(1, u.getPointValue());
		assertEquals(1, A.getPointValue());
		assertEquals(1, E.getPointValue());
		assertEquals(1, I.getPointValue());
		assertEquals(1, O.getPointValue());
		assertEquals(1, N.getPointValue());
		assertEquals(1, R.getPointValue());
		assertEquals(1, T.getPointValue());
		assertEquals(1, L.getPointValue());
		assertEquals(1, S.getPointValue());
		assertEquals(1, U.getPointValue());

	}

	@Test
	public void shouldCreateTwoPointTiles() {
		Tile d = new Tile('d');
		Tile g = new Tile('g');
		Tile D = new Tile('D');
		Tile G = new Tile('G');
		assertEquals(2, d.getPointValue());
		assertEquals(2, g.getPointValue());
		assertEquals(2, D.getPointValue());
		assertEquals(2, G.getPointValue());
	}

	@Test
	public void shouldCreateThreePointTiles() {
		Tile b = new Tile('b');
		Tile c = new Tile('c');
		Tile m = new Tile('m');
		Tile p = new Tile('p');
		Tile B = new Tile('B');
		Tile C = new Tile('C');
		Tile M = new Tile('M');
		Tile P = new Tile('P');
		assertEquals(3, b.getPointValue());
		assertEquals(3, c.getPointValue());
		assertEquals(3, m.getPointValue());
		assertEquals(3, p.getPointValue());
		assertEquals(3, B.getPointValue());
		assertEquals(3, C.getPointValue());
		assertEquals(3, M.getPointValue());
		assertEquals(3, P.getPointValue());
	}

	@Test
	public void shouldCreateFourPointTiles() {
		Tile f = new Tile('f');
		Tile h = new Tile('h');
		Tile v = new Tile('v');
		Tile w = new Tile('w');
		Tile y = new Tile('y');
		Tile F = new Tile('F');
		Tile H = new Tile('H');
		Tile V = new Tile('V');
		Tile W = new Tile('W');
		Tile Y = new Tile('Y');
		assertEquals(4, f.getPointValue());
		assertEquals(4, h.getPointValue());
		assertEquals(4, v.getPointValue());
		assertEquals(4, w.getPointValue());
		assertEquals(4, y.getPointValue());
		assertEquals(4, F.getPointValue());
		assertEquals(4, H.getPointValue());
		assertEquals(4, V.getPointValue());
		assertEquals(4, W.getPointValue());
		assertEquals(4, Y.getPointValue());
	}

	@Test
	public void shouldCreateFivePointTiles() {
		Tile k = new Tile('k');
		Tile K = new Tile('K');
		assertEquals(5, k.getPointValue());
		assertEquals(5, K.getPointValue());
	}

	@Test
	public void shouldCreateEightPointTiles() {
		Tile j = new Tile('j');
		Tile x = new Tile('x');
		Tile J = new Tile('J');
		Tile X = new Tile('X');
		assertEquals(8, j.getPointValue());
		assertEquals(8, x.getPointValue());
		assertEquals(8, J.getPointValue());
		assertEquals(8, X.getPointValue());
	}

	@Test
	public void shouldCreateTenPointTiles() {
		Tile q = new Tile('q');
		Tile z = new Tile('z');
		Tile Q = new Tile('Q');
		Tile Z = new Tile('Z');
		assertEquals(10, q.getPointValue());
		assertEquals(10, z.getPointValue());
		assertEquals(10, Q.getPointValue());
		assertEquals(10, Z.getPointValue());
	}
}
