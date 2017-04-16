//package plittr.converter;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//import plittr.entity.Plitter;
//import plittr.service.PlitterService;
// 
//
// 
///**
// * A converter class used in views to map id's to actual userProfile objects.
// */
//@Component
//public class PlitterConverter implements Converter<Object, Plitter>{
// 
//    static final Logger logger = LoggerFactory.getLogger(PlitterConverter.class);
//     
//    @Autowired
//    PlitterService plitterService;
// 
//    /**
//     * Gets UserProfile by Id
//     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
//     */
//    public Plitter convert(Object element) {
//        Integer id = Integer.parseInt((String)element);
//        Plitter profile= plitterService.findById(id);
//        logger.info("Profile : {}",profile);
//        return profile;
//    }
//     
//}