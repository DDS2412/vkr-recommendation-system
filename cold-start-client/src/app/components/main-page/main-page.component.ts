import {Component, OnInit} from '@angular/core';
import {ApiServiceService} from '../../services/api-service.service';
import {ColdStartEventsDto} from '../../models/cold.start.events.dto';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
  surveyEvents: ColdStartEventsDto;
  surveyMode: boolean;
  userId: number;

  constructor(private api: ApiServiceService) {
    this.surveyMode = true;
  }

  ngOnInit(): void {
  }

  startSurvey(): void {
    this.api
      .getSurveyEvents()
      .subscribe(
        (data: ColdStartEventsDto) => {
          this.surveyMode = false;
          this.surveyEvents = data;
          this.userId = Math.floor((Math.random() * 100000000) + 1);
      },
      err => {
        console.log(err);
      }
    );
  }
}
