import {ColdStartEventInfoDto} from './cold.start.event.info.dto';

export class ColdStartEventsDto {
  coldStartEventInfoDtos: ColdStartEventInfoDto[];
  eventsPerPage: number;
  npages: number;
}

