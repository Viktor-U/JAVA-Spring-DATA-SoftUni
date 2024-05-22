package bg.softuni.advqueringapplication.services;


import bg.softuni.advqueringapplication.entities.Label;
import bg.softuni.advqueringapplication.repositories.LabelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {

    private LabelRepository labelRepository;

    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }


}
