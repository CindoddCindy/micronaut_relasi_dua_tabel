
package cindy.duatabel.intfc;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import cindy.duatabel.model.TabelSatu;

public interface TabelSatuInterface {

    Long size();
    List<TabelSatu> findAll(int page, int limit); 
    TabelSatu findById(@NotNull Long id);
    boolean update(@NotNull Long id, String name, String email);
    Long save(@NotNull TabelSatu tabelSatu);
    boolean destroy(@NotNull Long id);
}