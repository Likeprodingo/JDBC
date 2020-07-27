package by.shibaev.jdbc.model.entity;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import by.shibaev.jdbc.model.util.*;


public class Book {
    private String name;
    private List<String> authors;
    private String publisher;
    public int publishYear;
    public String id;

    public Book(String name, List<String> authors, String publisher, int publishYear) {
        this.name = name;
        this.authors = authors;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.id = IDGenerator.generate();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (publishYear != book.publishYear) return false;
        if (name != null ? !name.equals(book.name) : book.name != null) return false;
        if (authors != null ? !authors.equals(book.authors) : book.authors != null) return false;
        if (publisher != null ? !publisher.equals(book.publisher) : book.publisher != null) return false;
        return id != null ? id.equals(book.id) : book.id == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + publishYear;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Book{");
        sb.append("name='").append(name).append('\'');
        sb.append(", authors=");
        for (String author : authors) {
            sb.append(author);
        }
        sb.append(", publisher='").append(publisher).append('\'');
        sb.append(", publishYear=").append(publishYear);
        sb.append(", id='").append(id).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static class bookComp implements Comparator<Book>{
        public int compare(Book b1, Book b2){
            int result = -1;
            if(b1.name.equals(b2.name) && b2.publisher.equals(b1.publisher) && b1.publishYear == b2.publishYear && new publisherComp().compare(b1,b2)==0){
                result = 0;
            }
            return result;
        }
    }

    public static class authorComp implements Comparator<Book> {
        public int compare(Book b1, Book b2) {
            int result = 0;
            List<String> a1 = b1.authors;
            List<String> a2 = b2.authors;
            Collections.sort(a1);
            Collections.sort(a2);
            Iterator<String> i1 = a1.iterator();
            Iterator<String> i2 = a2.iterator();
            while (i1.hasNext() && i2.hasNext()) {
                result = i1.next().compareTo(i2.next());
                if (result != 0) {
                    break;
                }
            }
            if (i2.hasNext() || i1.hasNext()) {
                result = -1;
            }
            return result;
        }

        public int compare(List<String> a1, List<String> a2) {
            int result = 0;
            Collections.sort(a1);
            Collections.sort(a2);
            Iterator<String> i1 = a1.iterator();
            Iterator<String> i2 = a2.iterator();
            while (i1.hasNext() && i2.hasNext()) {
                result = i1.next().compareTo(i2.next());
                if (result != 0) {
                    break;
                }
            }
            if (i2.hasNext() || i1.hasNext()) {
                result = -1;
            }
            return result;
        }
    }

    public static class nameComp implements Comparator<Book> {
        public int compare(Book b1, Book b2) {
            return b1.name.compareTo(b2.name);
        }
    }

    public static class idComp implements Comparator<Book> {
        public int compare(Book b1, Book b2) {
            return b1.id.compareTo(b2.id);
        }
    }

    public static class publisherComp implements Comparator<Book> {
        public int compare(Book b1, Book b2) {
            return b1.publisher.compareTo(b2.publisher);
        }
    }

    public static class yearComp implements Comparator<Book> {
        public int compare(Book b1, Book b2) {
            int result = 0;
            if (b1.getPublishYear() > b2.getPublishYear()) {
                result = 1;
            }
            if (b1.getPublishYear() < b2.getPublishYear()) {
                result = -1;
            }
            return result;
        }
    }
}
