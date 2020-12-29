package com.nao.twitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class twitter {
	public static void main(String[] args) {
        //コンシューマ・キーとコンシューマ・シークレット
        final String m_ConsumerKey = "3AU31DHCjIxzvJAksszJqXWn7";
        final String m_ConsumerSecret = "xJ1DLoad4AfBYr8Rt1VNSE5kYXFCDxohYrEQWhf9DfG131YURG";
        Twitter twitter;

        //アクセストークンの読み込み
        AccessToken accessToken = loadAccessToken();

        //アクセストークンが既に保存されていれば，それを利用して
        //Twitter 認証を行う．さもなくば，アクセストークンを取りにいく．
        try {
            if(accessToken != null){
                //自分のディレクトリに保存していた Access Token を利用する．
                twitter = new TwitterFactory().getInstance();
                twitter.setOAuthConsumer(m_ConsumerKey, m_ConsumerSecret);
                twitter.setOAuthAccessToken(accessToken);
            } else {
                twitter = new TwitterFactory().getInstance();
                twitter.setOAuthConsumer(m_ConsumerKey, m_ConsumerSecret);
                //アクセストークンの取得
                accessToken = getOAuthAccessToken(twitter);

                //自分のディレクトリに Access Token を保存しておく
                storeAccessToken(accessToken);
            }

            //自分のステータスの更新（＝ツイートの投稿）;
            Status status = twitter.updateStatus("変更します");
            System.out.println("Successfully updated the status to [" + status.getText() + "].");
            User user = twitter.verifyCredentials();
            System.out.println(user.getDescription());
        } catch(Exception e){
            System.err.println(e);
            System.exit(1);
        }
    }

    //アクセストークンの取得
    static AccessToken getOAuthAccessToken(Twitter twitter){
        RequestToken requestToken = null;
        AccessToken accessToken = null;

        try {
            //リクエストトークンの作成
            //(メモ) アクセストークンを取得後，保存して再利用するならば
            // リクエストトークンの作成は１度きりでよい．
            requestToken = twitter.getOAuthRequestToken();

            //ブラウザで Twitter 認証画面を表示するよう促し，
            // PIN コードを入力させ，アクセストークンを作成（取得）する
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (null == accessToken) {
                System.out.println("Open the following URL and grant access to your account:");
                System.out.println(requestToken.getAuthorizationURL());
                System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
                String pin = br.readLine();
                try{
                    if(pin.length() > 0){
                        accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                    }else{
                        accessToken = twitter.getOAuthAccessToken();
                    }
                } catch (TwitterException te) {
                    if(401 == te.getStatusCode()){
                        System.out.println("Unable to get the access token.");
                    }else{
                        te.printStackTrace();
                    }
                }
            } //while()
        } catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        return accessToken;
    }

    //アクセストークンの読み込み
    private static AccessToken loadAccessToken(){
        File f = createAccessTokenFileName();

        ObjectInputStream is = null;
        try {
            is = new ObjectInputStream(new FileInputStream(f));
            AccessToken accessToken = (AccessToken) is.readObject();
            return accessToken;
        } catch (IOException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //アクセストークンの保存
    private static void storeAccessToken(AccessToken accessToken){
        //ファイル名の生成
        File f = createAccessTokenFileName();

        //親ディレクトリが存在しない場合，親ディレクトリを作る．
        File d = f.getParentFile();
        if (!d.exists()) { d.mkdirs(); }

        //ファイルへの書き込み
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(new FileOutputStream(f));
            os.writeObject(accessToken);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // アクセストークンを保存するファイル名を生成する
    static File createAccessTokenFileName() {
        // (メモ) System.getProperty("user.home") の返し値は
        // ホームディレクトリの絶対パス
        String s = System.getProperty("user.home") + "/.twitter/client/sample/accessToken.dat";
        return new File(s);
    }
}

