package dio.java.collection.set2;

/*
Dadas as seguintes informações sobre minhas séries favoritas,
crie um conjunto e ordene este conjunto exibindo:
(tempo - genero - tempo de episódio);

Série 1 = Nome: got, genero: fantasia, tempoEpisódio: 60
Série 2 = Nome: dark, genero: drama, tempoEpisódio: 60
Série 3 = Nome: that '70s show, genero: comédia, tempoEpisódio: 25
 */

import java.util.*;

public class ExemploOrdenacaoSet {
    public static void main(String[] args) {
        System.out.println("--\tOrdem Aleatória\t--");
        Set<Series> minhasSeries = new HashSet<>() {{
            add(new Series("got", "fantasia", 60 ));
            add(new Series("dark", "drama", 60 ));
            add(new Series("that '70s show", "comédia", 25 ));
        }};
        for (Series series: minhasSeries) System.out.println(series.getNome() + " - " + series.getGenero() + " - "  + series.getTempoEpisodio());

        System.out.println("--\tOrdem Inserção\t--");
        Set<Series> minhasSeries1 = new LinkedHashSet<>() {{
            add(new Series("got", "fantasia", 60 ));
            add(new Series("dark", "drama", 60 ));
            add(new Series("that '70s show", "comédia", 25 ));
        }};
        for (Series series: minhasSeries1) System.out.println(series.getNome() + " - " + series.getGenero() + " - " + series.getTempoEpisodio());

        System.out.println("--\tOrdem natural (TempoEpisódio)\t--");
        Set<Series> minhasSeries2 = new TreeSet<>(minhasSeries1);

        for (Series series: minhasSeries2) System.out.println(series.getNome() + " - " + series.getGenero() + " - " + series.getTempoEpisodio());


        System.out.println("--\tOrdem Nome, genero, tempoEpisódio\t--");
        Set<Series> minhasSeries3 = new TreeSet<>(new ComparatoNomeGeneroTempoEpisodio());
        minhasSeries3.addAll(minhasSeries);
        for (Series series: minhasSeries2) System.out.println(series.getNome() + " - " + series.getGenero() + " - " + series.getTempoEpisodio());


        //Para você:

        //System.out.println("--\tOrdem genero\t--");
        //System.out.println("--\tOrdem tempo episódio\t--");
    }
}

class Series implements Comparable<Series> {
    private String Nome;
    private String genero;
    private Integer tempoEpisodio;

    public Series(String nome, String genero, Integer tempoEpisodio) {
        Nome = nome;
        this.genero = genero;
        this.tempoEpisodio = tempoEpisodio;
    }

    public String getNome() {
        return Nome;
    }

    public String getGenero() {
        return genero;
    }

    public Integer getTempoEpisodio() {
        return tempoEpisodio;
    }

    @Override
    public String toString() {
        return "{" +
                "Nome='" + Nome + '\'' +
                ", genero='" + genero + '\'' +
                ", tempoEpisodio='" + tempoEpisodio + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Series series = (Series) o;
        return Nome.equals(series.Nome) && genero.equals(series.genero) && tempoEpisodio.equals(series.tempoEpisodio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Nome, genero, tempoEpisodio);
    }

    @Override
    public int compareTo(Series series) {
        int tempoEpisodio = Integer.compare(this.getTempoEpisodio(), series.getTempoEpisodio());
        if (tempoEpisodio != 0) return tempoEpisodio;
        return this.getGenero().compareTo(series.getGenero());
    }
}

class ComparatoNomeGeneroTempoEpisodio implements Comparator<Series> {

    @Override
    public int compare(Series s1, Series s2) {
        int nome = s1.getNome().compareTo(s2.getNome());
        if (nome != 0) return nome;

        int genero = s1.getGenero().compareTo(s2.getGenero());
        if (genero != 0) return genero;

        return Integer.compare(s1.getTempoEpisodio(), s2.getTempoEpisodio());
    }
}