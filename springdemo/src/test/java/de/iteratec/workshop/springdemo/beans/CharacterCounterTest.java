package de.iteratec.workshop.springdemo.beans;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CharacterCounterTest {

    private final CharacterCounter characterCounter = new CharacterCounter('A', 'E', 'I', 'O', 'U');

    @Test
    void count_1() {
        // given
        String text = "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // when
        long count = characterCounter.count(text);
        // then
        assertThat(count).isEqualTo(10);
    }

    @Test
    void count_2() {
        // given
        String text = "bcdfghjklmnpqrstvwxyz BCDFGHJKLMNPQRSTVWXYZ";
        // when
        long count = characterCounter.count(text);
        // then
        assertThat(count).isEqualTo(0);
    }

    @Test
    void count_3() {
        // given
        String text = "AAAh!";
        // when
        long count = characterCounter.count(text);
        // then
        assertThat(count).isEqualTo(3);
    }

    @Test
    void count_4() {
        // given
        String text = "";
        // when
        long count = characterCounter.count(text);
        // then
        assertThat(count).isEqualTo(0);
    }
}