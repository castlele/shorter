управления короткими ссылками с дополнительными функциями: ограничение по
количеству переходов, возможность управления ссылками и их автоматического
удаления при достижении лимита или истечении времени.

Пользователь может устанавливать лимит на количество переходов по ссылке и
отслеживать её активность. После достижения лимита переходов или истечения
заданного срока ссылка станет недоступной, и пользователь получит уведомление с
предложением создать новую ссылку.

## Интерфейс управления

```bash
shorter init
shorter init --uuid 550e8400-e29b-41d4-a716-446655440000
shorter init --name castlelecs --uuid 550e8400-e29b-41d4-a716-446655440000
shorter init --name castlelecs

shorter convert "https://luarocks.org/modules/castlelecs" --user castlelecs
shorter convert "https://luarocks.org/modules/castlelecs" --auth 550e8400-e29b-41d4-a716-446655440000
shorter convert "" --user castlelecs --timelimit 2 --uselimit 10
```
