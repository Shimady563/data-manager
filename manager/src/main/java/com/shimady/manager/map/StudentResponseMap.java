package com.shimady.manager.map;

import com.shimady.manager.model.Major;
import com.shimady.manager.model.Student;
import com.shimady.manager.model.dto.StudentResponse;
import org.modelmapper.PropertyMap;

public class StudentResponseMap extends PropertyMap<Student, StudentResponse> {
    @Override
    protected void configure() {
        map(source.getId(), destination.getId());
        map(source.getFullName(), destination.getFullName());
        map(source.getCourse(), destination.getCourse());
        map(source.getGroup(), destination.getGroup());
        map(source.getHasRecordBook(), destination.getHasRecordBook());
        using((m) -> ((Major) m.getSource()).getName())
                .map(source.getMajor(), destination.getMajorName());
    }
}

