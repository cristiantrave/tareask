package com.cristian.tareask.dao;

import java.util.List;
import com.cristian.tareask.model.Incidence;

public interface IncidenceDao {
    public void add (Incidence incidence);
    public void edit (Incidence incidence);
    public void delete (int IncidenceId);
    public Incidence getIncidence (int IncidenceId);
    public List getAllIncidences();
    public List<Incidence> getIncidencesByTaskId(int TaskId);
}