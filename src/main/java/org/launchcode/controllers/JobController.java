package org.launchcode.controllers;

import org.launchcode.models.*;
import org.launchcode.models.data.JobDataImporter;
import org.launchcode.models.data.JobFieldData;
import org.launchcode.models.forms.JobForm;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "job")
public class JobController {

    private JobData jobData = JobData.getInstance();

    // The detail display for a given Job at URLs like /job?id=17
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, Integer id) {

       // JobFieldType[] fields = JobFieldType.values();
       // TODO #1 - get the Job with the given ID and pass it into the view

   ////     Job job =  new Job();
   ////     job = JobData.getInstance().findById(id);
      //  Job someJob = jobData.findById(42);
        Job job = jobData.findById(id);
        model.addAttribute("id",id);
        model.addAttribute("job",job);
   //     model.addAttribute("fields",fields);
  ////      model.addAttribute("job", job);
      //   model.addAttribute("job", someJob);
        return "job-detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new JobForm());
        return "new-job";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @Valid JobForm jobForm, Errors errors) {
        // here is where we should have the "public String handlerMethod(Model model,@ModelAttribute @Valid someObject, Errors errors)"
        // TODO #6 - Validate the JobForm model, and if valid, create a
        // new Job and add it to the jobData data store. Then
        // redirect to the job detail view for the new Job.
        //JobData.add(jobForm);
        //JobForm jobForm1 = JobData.getInstance().add();
       // String name = JobForm.class.getName();
       // System.out.println(name);


        //Job newJob = new Job(record.get("name"), emp, loc, posType, coreComp);

        //jobData.add(newJob);

        //JobData.getInstance().add(Job );
        if(errors.hasErrors()){
            return "new-job";
        }

        String name = jobForm.getName();


      //  Employer employer = JobData.getInstance().findById(jobForm.getEmployerId()); problem version
        Employer employer = jobData.getEmployers().findById(jobForm.getEmployerId());

     //   Location location = JobData.getInstance().findById(jobForm.getLocationId());
        Location location = jobData.getLocations().findById(jobForm.getLocationId());

     //   PositionType positionType = JobData.getInstance().findById(jobForm.getPositionTypeId());
        PositionType positionType = jobData.getPositionTypes().findById(jobForm.getPositionTypeId());

     //   CoreCompetency coreCompetency = JobData.getInstance().findById(jobForm.getCoreCompetencyId());
        CoreCompetency coreCompetency = jobData.getCoreCompetencies().findById(jobForm.getCoreCompetencyId());

        Job job = new Job(name , employer, location, positionType, coreCompetency);

        jobData.add(job);
        model.addAttribute("job", job);

        return "job-detail";

    }
}
