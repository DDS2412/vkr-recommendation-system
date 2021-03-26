import {Component, Input, OnInit} from '@angular/core';
import {ColdStartEventsDto} from '../../models/cold.start.events.dto';
import {ColdStartEventInfoDto} from '../../models/cold.start.event.info.dto';
import {FormControl, Validators} from '@angular/forms';
import {ColdStartAnswerDto} from '../../models/cold.start.answer.dto';
import {ApiServiceService} from '../../services/api-service.service';

@Component({
  selector: 'app-survey-page',
  templateUrl: './survey-page.component.html',
  styleUrls: ['./survey-page.component.css']
})
export class SurveyPageComponent implements OnInit {
  @Input() surveyEvents: ColdStartEventsDto;
  @Input() userId: number;

  pullQuestions: string[];
  currentQuestion: string;
  isEndOfSurvey: boolean;
  selectFormControl = new FormControl('', Validators.required);

  currentPage: number;

  currentEvents: ColdStartEventInfoDto[];
  selectedEvent: ColdStartEventInfoDto;

  coldStartAnswers: ColdStartAnswerDto[];

  constructor(private api: ApiServiceService) {
    this.isEndOfSurvey = false;

    this.pullQuestions = [
      'Какое из мероприятий вам ближе по душе?', 'Что вам больше нравится?', 'Куда бы вы хотели сходить?'
    ];

    this.currentQuestion = this.pullQuestions[Math.floor(Math.random() * this.pullQuestions.length)];
    this.currentPage = 0;
    this.coldStartAnswers = [];
  }

  ngOnInit(): void {
    this.currentEvents = this.surveyEvents.coldStartEventInfoDtos.slice(0, this.surveyEvents.eventsPerPage);
  }

  getNextQuestion(): void {
    this.currentPage += 1;
    this.coldStartAnswers.push(new ColdStartAnswerDto(this.currentEvents.map(value => value.id), this.selectedEvent));

    this.selectedEvent = null;

    if (this.currentPage < this.surveyEvents.npages) {
      this.currentQuestion = this.pullQuestions[Math.floor(Math.random() * this.pullQuestions.length)];
      this.currentEvents = this.surveyEvents.coldStartEventInfoDtos
        .slice(this.currentPage * this.surveyEvents.eventsPerPage, (this.currentPage + 1) * this.surveyEvents.eventsPerPage);

    } else {
      this.isEndOfSurvey = true;
      this.api.setAnswersForSurvey(this.coldStartAnswers, this.userId);
    }
  }
}
