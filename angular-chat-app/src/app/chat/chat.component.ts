import { Component } from '@angular/core';
import { ChatService } from '../service/chat.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent {
  message: string = '';

  constructor(private chatService: ChatService) {}

  updateMessage(event: Event) {
    this.message = (event.target as HTMLInputElement).value;
  }

  send() {
    console.log(this.message);
    if (this.message.trim()) {
      this.chatService.addMessage(this.message);
      this.message = '';
    }
  }

  get messages(): string[] {
    return this.chatService.getMessages();
  }
}
