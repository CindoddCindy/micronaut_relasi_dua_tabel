package cindy.duatabel.intfc;

import cindy.duatabel.model.*;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface TabelDuaInterface {

    List<TabelDua> findAll(int page, int limit);
    Long size();
    TabelDua save(@NotNull TabelDua tabelDua);
    TabelDua update(@NotNull Long id, TabelDua tabelDua);
    TabelDua findById(@NotNull Long id);
    TabelDua destroy(@NotNull Long id);

}