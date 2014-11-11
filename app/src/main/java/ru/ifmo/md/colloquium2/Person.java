package ru.ifmo.md.colloquium2;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
* Created by юзер on 11.11.2014.
*/
final class Person implements Comparable<Person>, Serializable {
    private final CharSequence name;
    private final int votesCount;

    Person(CharSequence name, int votesCount) {
        this.name = name;
        this.votesCount = votesCount;
    }

    public CharSequence getName() {
        return name;
    }

    public int getVotesCount() {
        return votesCount;
    }

    @Override
    public int compareTo(Person another) {
        return another.votesCount - votesCount;
    }
}
