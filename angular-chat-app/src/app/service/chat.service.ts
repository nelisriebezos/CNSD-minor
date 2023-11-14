import { Injectable } from '@angular/core';
import { Subscription } from 'rxjs';
import { WebsocketService } from './websocket.service';


@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private messages: string[] = [];
  private subscription: Subscription;


  constructor(private websocketService: WebsocketService) {
    this.subscription = websocketService.messages$.subscribe(
      (message: any) => {
        console.log(message);
        this.addMessage(message.message);
      }
    );
  }

  sendMessage(message: string): void {
    this.websocketService.sendMessage(message);
  }

  addMessage(message: string): void {
    this.messages.push(message);
  }

  getMessages(): string[] {
    return this.messages;
  }

  unSubScribeFromWebsocket(): void {
    this.subscription.unsubscribe(); // Don't forget to unsubscribe
  }
}
