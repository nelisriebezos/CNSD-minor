import { Injectable } from '@angular/core';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';
import { Subject } from 'rxjs';

interface MessageDTO {
  action: string;
  data: string;
}

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  private websocket: WebSocketSubject<any>;
  private messageSubject = new Subject<string>();
  readonly messages$ = this.messageSubject.asObservable();


  constructor() {
    this.websocket = webSocket('wss://z4dyewdmpk.execute-api.us-east-1.amazonaws.com/Prod');

    this.websocket.subscribe(
      msg => {
        this.messageSubject.next(msg);
      },
      err => {
        console.error(err);
      },
      () => {
        console.log('WebSocket connection closed');
      }
    );
  }


  sendMessage(message: string): void {    
    const dto: MessageDTO = {
      "action": "sendmessage",
      "data": message
    }

    this.websocket.next(dto);
  }

  close(): void {
    this.websocket.complete(); // Close the connection
  }
}