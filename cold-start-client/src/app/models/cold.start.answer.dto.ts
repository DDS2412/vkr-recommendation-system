export class ColdStartAnswerDto {
  constructor(eventIds, favoriteId) {
    this.eventIds = eventIds;
    this.favoriteId = favoriteId;
}

  eventIds: number[];
  favoriteId: number;
}
