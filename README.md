# FcTronto
- チームFC-TRONTO ロボットサッカーマインドストーム側プログラム
- RaspberryPi側は[こちら](https://github.com/FC-TRONTO/raspi_FcTronto)にあります
## 使用OSについて
- 使用OS : [leJOS](http://www.lejos.org/)
- Java仮想マシンを含み、レゴマインドストームのロボットにJavaでプログラムを施す事が出来る
## leJOSを採用した経緯
- 最初は以下のプラットフォームを検討していた
  - [EV3RT](https://dev.toppers.jp/trac_user/ev3pf/wiki/WhatsEV3RT) : 国内で最もメジャーそうなプラットフォーム
  - [ev3dev](https://www.ev3dev.org/) : ラズパイ側はPythonで実装するため、マインドストーム側もPythonだと書きやすいかなと思った
  - [LEGO MINDSTORMS Education EV3](https://education.lego.com/ja-jp/downloads/mindstorms-ev3/software) : 昨年のロボサッカーの使用実績あり
- ただ、EV3RT・ev3devについては[IRシーカー](https://www.hitechnic.com/cgi-bin/commerce.cgi?preadd=action&key=NSK1042)に対応していなかったため、不採用とした
  - 多分自分でAPIをいじる勢いでやれば利用できるのだろうが、そこまではやりたくなかった
- LEGO MINDSTORMS Education EV3はブロックプログラミングであり、実装がかなりしんどいため不採用
- 結局LEGO MINDSTORMS Education EV3以外ではleJOSのみがIRシーカーに対応していたため、leJOSを採用した
## leJOS開発環境について
- 開発環境構築
  - 以下のサイトが参考になる、つまるようなところは特にないはず
    - 参考 : https://github.com/ETrobocon/etroboEV3/wiki/lejosev3_win
  - Eclipseプラグインがかなり有用、プログラム転送・実行をすべてリモートでできる
## ラズパイとの接続
- 今回はシリアル通信を利用した
- 使用部品
  - [6極6芯モジュラージャックDIP化キット](http://akizukidenshi.com/catalog/g/gK-09688/)
    - マインドストーム側のケーブルを接続、そのままだと線が挿せないためはんだで溶かして口を拡げた
  - [FT234X 超小型USBシリアル変換モジュール](http://akizukidenshi.com/catalog/g/gM-08461/)
    - ラズパイ側はUSB-TypeBで接続
- 接続図
  - マインドストームのセンサーポート<->マインドストームのケーブル<->6極6芯モジュラージャックDIP化キット<->FT234X 超小型USBシリアル変換モジュール<->USBケーブル<->ラズパイ
- 6極6芯モジュラージャックとUSBシリアル変換モジュール間の配線は以下のサイトが参考になる
  - 参考 : https://nxt.typepad.jp/robojoy/2017/08/usbserial.html
## プログラムのマニュアル
- 本プログラムはdoxygenでドキュメント化しています
- html/index.htmlをブラウザで開いてください

