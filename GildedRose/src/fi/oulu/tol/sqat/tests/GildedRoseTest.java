package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

	@Test
	public void testTheTruth() {
		assertTrue(true);
	}
	@Test
	public void pastTheConsertDoubleDegrade() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Conjured Mana Cake", 9, 15));
		for (int i=0; i<10; i++) {
			inn.oneDay();
		}
		
		List<Item> items = inn.getItems();
		
		assertEquals("Did not double degrade after concert", 4, items.get(0).getQuality());
		
		for (int i=0; i<10; i++) {
			inn.oneDay();
		}
		
		items = inn.getItems();
		
		assertEquals("Did not stop at zero", 0, items.get(0).getQuality());
	}
	@Test
	public void qualityOver50orUnder0() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Conjured Mana Cake", 10, 6));
		inn.setItem(new Item("Elixir of the Mongoose", 10, 6));
		inn.setItem(new Item("+5 Dexterity Vest", 10, 6));
		inn.setItem(new Item("Aged Brie", 10, 44));
	
		for (int i=0; i<10; i++) {
			inn.oneDay();
		}
		
		List<Item> items = inn.getItems();
		
		assertEquals("Degrading did not stop at zero", 0, items.get(0).getQuality());
		assertEquals("Degrading did not stop at zero", 0, items.get(1).getQuality());
		assertEquals("Degrading did not stop at zero", 0, items.get(2).getQuality());
		assertEquals("Aging did not stop at 50", 50, items.get(3).getQuality());
		
	}
	@Test
	public void normalStuffTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 5, 20));
		for (int i=0; i<5; i++) {
			inn.oneDay();
		}
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 15, quality);
		
		for (int i=0; i<5; i++) {
			inn.oneDay();
		}
		
		items = inn.getItems();
		quality = items.get(0).getQuality();
		
		assertEquals("Quality did not degrade correctly", 5, quality);
	}
	@Test
	public void sulfurasDoesNotChange() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", -1, 80));
		for (int i = 0; i < 100; i++) {
			inn.oneDay();
		}
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Quality changed", 80, quality);
	}
	@Test
	public void agedBrieAging() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 5, 0));
		for (int i=0; i < 5; i++) {
			inn.oneDay();
		}
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Does not increase quality by 1", 5, quality);
		
		for (int i=0; i < 5; i++) {
			inn.oneDay();
		}
		items = inn.getItems();
		quality = items.get(0).getQuality();
		
		assertEquals("Does not increase quality by 2", 15, quality);
	}
	@Test
	public void backStagePassAging() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 0));
		for (int i=0; i < 5; i++) {
			inn.oneDay();
		}
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Does not increase quality by 1", 5, quality);
		
		for (int i=0; i < 5; i++) {
			inn.oneDay();
		}
		items = inn.getItems();
		quality = items.get(0).getQuality();
		
		assertEquals("Does not increase quality by 2", 15, quality);
		
		for (int i=0; i < 4; i++) {
			inn.oneDay();
		}
		items = inn.getItems();
		quality = items.get(0).getQuality();
		
		assertEquals("Does not increase quality by 3", 27, quality);
		
		for (int i=0; i < 10; i++) {
			inn.oneDay();
		}
		items = inn.getItems();
		quality = items.get(0).getQuality();
		assertEquals("Does not drop value to zero on sellIn==0", 0, quality);
	}
	@Test
	public void advancedBackStageAging1() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 11, 48));
		for (int i=0; i < 3; i++) {
			inn.oneDay();
		}
		
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Aging does not stop at 50", 50, quality);
	}
	@Test
	public void advancedBackStageAging2() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 6, 46));
		for (int i=0; i < 3; i++) {
			inn.oneDay();
		}
		
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Aging does not stop at 50", 50, quality);
	}
	@Test
	public void agedBrieAgingAdvanced() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 2, 48));
		for (int i=0; i < 10; i++) {
			inn.oneDay();
		}
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Does not increase quality by 1", 50, quality);
	}
}
