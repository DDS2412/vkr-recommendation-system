import { Component, OnInit } from '@angular/core';
import {ApiServiceService} from '../../services/api-service.service';
import {ColdStartEventInfoDto} from '../../models/cold.start.event.info.dto';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
  surveyMode: boolean;
  surveyEvents: ColdStartEventInfoDto[];

  constructor(private api: ApiServiceService) {
    this.surveyMode = true;
  }

  ngOnInit(): void {
  }

  startSurvey(): void {
    this.api
      .getSurveyEvents()
      .subscribe(
        (data: ColdStartEventInfoDto[]) => {
          this.surveyEvents = data;
          this.surveyMode = false;
      },
      err => {
        console.log(err);
      }
    );
  }

  getNextQuestion(): void {
    this.surveyMode = false;
  }
}
